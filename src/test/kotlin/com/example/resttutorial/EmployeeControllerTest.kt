package com.example.resttutorial

import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {


    @MockBean
    private lateinit var repository: EmployeeRepository

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun getEmployees() {
        mockMvc.perform(get("/employees"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$._links.self.href").value("http://localhost/employees"))
        // .andExpect(jsonPath("$._embedded").doesNotExist())

        verify(repository).findAll()
    }
}