package hr.perisic.luka.domain.model

data class MapModel(
    val url: String,
    val size: Size
) {

    data class Size(
        val width: Int,
        val height: Int
    )
}