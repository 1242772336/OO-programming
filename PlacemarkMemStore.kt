import mu.KotlinLogging
import org.wit.placemark.console.models.PlacemarkModel
import org.wit.placemark.console.models.PlacemarkStore

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PlacemarkMemStore() : PlacemarkStore {

    val placemarks = ArrayList<PlacemarkModel>()

    fun findAll(): List<PlacemarkModel> {
        return placemarks
    }

    fun findOne(id: Long) : PlacemarkModel? {
        var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == id }
        return foundPlacemark
    }

    fun create(placemark: PlacemarkModel) {
        placemark.id = getId()
        placemarks.add(placemark)
        logAll()
    }

    fun update(placemark: PlacemarkModel) {
        var foundPlacemark = findOne(placemark.id!!)
        if (foundPlacemark != null) {
            foundPlacemark.title = placemark.title
            foundPlacemark.description = placemark.description
        }
    }

    internal fun logAll() {
        placemarks.forEach { logger.info("${it}") }
    }
}

