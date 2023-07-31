import com.desafio.jumarket.dtos.ClienteDTO
import com.desafio.jumarket.exception.CpfJaCadastradoException
import com.desafio.jumarket.exception.DataNotFoundException
import com.desafio.jumarket.models.Cliente
import com.desafio.jumarket.repositories.ClienteRepository
import com.desafio.jumarket.services.impl.ClienteServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class ClienteServiceImplTest {

    @Mock
    private lateinit var clienteRepository: ClienteRepository

    @InjectMocks
    private lateinit var clienteService: ClienteServiceImpl


    @Test
    fun `test criarCliente should create a new Cliente`() {

        val clienteDTO = ClienteDTO(
            id = null,
            nome = "Novo Cliente",
            cpf = "123.456.789-00"
        )
        val cliente = Cliente(
            id = 1L,
            nome = "Novo Cliente",
            cpf = "123.456.789-00"
        )
        `when`(clienteRepository.findFirstByCpf(clienteDTO.cpf)).thenReturn(Optional.empty())
        `when`(clienteRepository.save(any())).thenReturn(cliente)


        val result = clienteService.criarCliente(clienteDTO)

        // Assert
        assertEquals(1L, result.id)
        assertEquals(clienteDTO.nome, result.nome)
        assertEquals(clienteDTO.cpf, result.cpf)
    }

    @Test
    fun `test criarCliente should throw CpfJaCadastradoException when CPF already exists`() {

        val clienteDTO = ClienteDTO(
            id = null,
            nome = "Novo Cliente",
            cpf = "123.456.789-00"
        )
        val cliente = Cliente(
            id = 1L,
            nome = "Cliente Existente",
            cpf = "123.456.789-00"
        )
        `when`(clienteRepository.findFirstByCpf(clienteDTO.cpf)).thenReturn(Optional.of(cliente))


        assertThrows(CpfJaCadastradoException::class.java) {
            clienteService.criarCliente(clienteDTO)
        }
    }

    @Test
    fun `test buscarClientePorId should return ClienteDTO when Cliente is found`() {

        val clienteId = 1L
        val cliente = Cliente(
            id = clienteId,
            nome = "Cliente de Teste",
            cpf = "987.654.321-00"
        )
        `when`(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente))


        val result = clienteService.buscarClientePorId(clienteId)

        // Assert
        assertNotNull(result)
        assertEquals(cliente.id, result?.id)
        assertEquals(cliente.nome, result?.nome)
        assertEquals(cliente.cpf, result?.cpf)
    }

    @Test
    fun `test buscarClientePorId should return null when Cliente is not found`() {

        val clienteId = 1L
        `when`(clienteRepository.findById(clienteId)).thenReturn(Optional.empty())


        assertThrows<DataNotFoundException> {
            clienteService.buscarClientePorId(clienteId)
        }
    }


    @Test
    fun `test listarClientes should return a list of ClienteDTOs`() {

        val clientes = listOf(
            Cliente(id = 1L, nome = "Cliente 1", cpf = "111.111.111-11"),
            Cliente(id = 2L, nome = "Cliente 2", cpf = "222.222.222-22")
        )
        `when`(clienteRepository.findAll()).thenReturn(clientes)


        val result = clienteService.listarClientes()


        assertEquals(clientes.size, result.size)
        assertEquals(clientes[0].id, result[0].id)
        assertEquals(clientes[0].nome, result[0].nome)
        assertEquals(clientes[0].cpf, result[0].cpf)
        assertEquals(clientes[1].id, result[1].id)
        assertEquals(clientes[1].nome, result[1].nome)
        assertEquals(clientes[1].cpf, result[1].cpf)
    }

    @Test
    fun `test atualizarCliente should update an existing Cliente`() {

        val clienteId = 1L
        val clienteDTO = ClienteDTO(
            id = clienteId,
            nome = "Cliente Atualizado",
            cpf = "999.999.999-99"
        )
        val cliente = Cliente(
            id = clienteId,
            nome = "Cliente Antigo",
            cpf = "123.456.789-00"
        )
        `when`(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente))
        `when`(clienteRepository.save(cliente)).thenReturn(cliente.copy(nome = clienteDTO.nome, cpf = clienteDTO.cpf))


        val result = clienteService.atualizarCliente(clienteId, clienteDTO)

        // Assert
        assertNotNull(result)
        assertEquals(clienteId, result?.id)
        assertEquals(clienteDTO.nome, result?.nome)
        assertEquals(clienteDTO.cpf, result?.cpf)
    }

    @Test
    fun `test atualizarCliente should throw DataNotFoundException when Cliente is not found`() {

        val clienteId = 1L
        val clienteDTO = ClienteDTO(
            id = clienteId,
            nome = "Cliente Atualizado",
            cpf = "999.999.999-99"
        )
        `when`(clienteRepository.findById(clienteId)).thenReturn(Optional.empty())


        assertThrows(DataNotFoundException::class.java) {
            clienteService.atualizarCliente(clienteId, clienteDTO)
        }
    }

    @Test
    fun `test excluirCliente should delete an existing Cliente`() {

        val clienteId = 1L
        val cliente = Cliente(
            id = clienteId,
            nome = "Cliente a ser excluído",
            cpf = "111.111.111-11"
        )
        `when`(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente))


        clienteService.excluirCliente(clienteId)


        verify(clienteRepository, times(1)).delete(cliente)
    }

    @Test
    fun `test excluirCliente should throw DataNotFoundException when Cliente is not found`() {

        val clienteId = 1L
        `when`(clienteRepository.findById(clienteId)).thenReturn(Optional.empty())

        assertThrows(DataNotFoundException::class.java) {
            clienteService.excluirCliente(clienteId)
        }
    }
}
