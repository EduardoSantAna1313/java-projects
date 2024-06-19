package br.com.edu.app.test.containers.infraestructure.database.entity

import br.com.edu.app.test.containers.domain.ApiParameter
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity(name = "parameters")
@Table(name ="API_PARAMETERS")
class ApiParameterEntity (

    @Id
    @Column(name = "ID")
    val id: String,

    @Column(name = "API_ID")
    val apiId: String,

    @Column(name = "PARAMETER_NAME")
    val name: String,

    @Column(name = "PARAMETER_VALUE")
    val value: String
) {

    fun toModel() = ApiParameter (
        UUID.fromString(id),
        UUID.fromString(apiId),
        name,
        value
    )

    companion object {

        fun fromModel(model: ApiParameter) = ApiParameterEntity(
            id = model.id.toString(),
            apiId = model.apiId.toString(),
            name = model.name,
            value = model.value
        )

    }

}