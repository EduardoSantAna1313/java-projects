package br.com.edu.app.test.containers.infraestructure.rest

import br.com.edu.app.test.containers.DatabaseConfiguration
import br.com.edu.app.test.containers.TestContainerApplication
import br.com.edu.app.test.containers.domain.Api
import br.com.edu.app.test.containers.infraestructure.dao.ApiDao
import br.com.edu.app.test.containers.domain.ApiStatus
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@Import(DatabaseConfiguration::class)
@SpringBootTest(
	classes = [ TestContainerApplication::class ],
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles( value = [ "test" ])
@AutoConfigureMockMvc
class ApiControllerMvcTest {

	@Autowired
	lateinit var mockMvc: MockMvc

	@Autowired
	lateinit var dao: ApiDao

	@AfterEach
	fun tearDown() {
		dao.deleteAll()
	}

	@Test
	fun `should return status 200`() {

		val maxRecords = 100

		val pageSize = 10

		for (i in 1..maxRecords) {
			val api = Api(
				name = "api $i",
				description = "Description $i",
				status = ApiStatus.CREATED,
				createdBy = "test user"
			)
			api.addParameter("title", "Title api $i")
			dao.create(api)
		}

		mockMvc.perform(get("/apis?page=0&limit=$pageSize"))
			.andDo{ println("""
					response.status: ${it.response.status}
				""".trimIndent())
			}
			.andExpect (status().isOk)
			.andExpect(jsonPath("$.page.size").value(pageSize))
			.andExpect(jsonPath("$.page.totalElements").value(maxRecords))
	}

}
