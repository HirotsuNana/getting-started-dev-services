package org.acme

import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import java.util.Random
import jakarta.persistence.NoResultException

@Path("/hello")
class GreetingResource {

    @Inject
    lateinit var entityManager: EntityManager
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): String {
        val randomId = getRandomId()
        val query = entityManager.createQuery("SELECT name FROM Users where id = :randomId")
        query.setParameter("randomId", randomId)
        return try {
            val name = query.singleResult as? String ?: ""
            "hello $name"
        } catch (e: NoResultException) {
            "No user found for id: $randomId"
        }
    }

    private fun getRandomId(): Long {
        val random = Random()
        return random.nextInt(5).toLong()
    }
    
}