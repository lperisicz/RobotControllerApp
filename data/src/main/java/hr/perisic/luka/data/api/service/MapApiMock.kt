package hr.perisic.luka.data.api.service

import hr.perisic.luka.domain.model.MapModel

internal class MapApiMock : MapApi {

    override suspend fun getMap(): MapModel {
        return MapModel(
            url = "https://answers.ros.org/upfiles/14139030516102171.png", //ByteArray(0)"//resources.getDrawable(R.drawable.map_v1).toBitmap().ninePatchChunk
            MapModel.Size(width = 1632,
            height = 1216)
        )
    }
}