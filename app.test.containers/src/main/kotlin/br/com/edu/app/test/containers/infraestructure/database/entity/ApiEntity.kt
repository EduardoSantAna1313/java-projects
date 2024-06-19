package br.com.edu.app.test.containers.infraestructure.database.entity

import br.com.edu.app.test.containers.domain.Api
import br.com.edu.app.test.containers.domain.ApiStatus
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "API")
class ApiEntity (

    @Id
    @Column(name = "ID")
    val id: String,

    @Column(name = "NAME")
    val name: String,

    @Column(name = "DESCRIPTION")
    val description: String,

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    val status: ApiStatus,

    @Column(name = "CREATED")
    val created: LocalDateTime,

    @Column(name = "CREATED_BY")
    val createdBy: String,

    @Column(name = "UPDATED")
    val updated: LocalDateTime,

    @Column(name = "UPDATED_BY")
    val updatedBy: String

) {

    fun toModel() = Api(
        id = UUID.fromString(id),
        name = name,
        description = description,
        status = status,
        createdBy = createdBy,
        updatedBy = updatedBy,
        created = created,
        updated = updated
    )

    companion object {
        fun fromModel(api: Api) = ApiEntity(
            id = api.id.toString(),
            name =  api.name,
            description =  api.description,
            status = api.status,
            created = api.created,
            createdBy = api.createdBy,
            updated = api.updated,
            updatedBy = api.updatedBy
        )
    }
}
