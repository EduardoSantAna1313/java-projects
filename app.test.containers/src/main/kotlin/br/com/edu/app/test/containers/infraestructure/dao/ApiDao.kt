package br.com.edu.app.test.containers.infraestructure.dao

import br.com.edu.app.test.containers.domain.Api
import br.com.edu.app.test.containers.infraestructure.database.entity.ApiEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
class ApiDao (
    private val repository: InternalRepository
) {

    fun create(api: Api) {
        repository.save(ApiEntity.fromModel(api))
    }

    fun list(pageable: Pageable): Page<Api> {
        val apis = repository.findAll(pageable)

        return apis.map { it.toModel() }
    }

    fun deleteAll() = repository.deleteAll()

}

@Repository
interface InternalRepository : PagingAndSortingRepository<ApiEntity, String>, JpaRepository<ApiEntity, String>
