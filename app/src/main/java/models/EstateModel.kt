package models

import com.example.r_android_template.service.Service
import org.json.JSONArray
import org.json.JSONTokener

data class EstateModel(
    val id: Int,
    val estateNo: String,
    val area: String,
    val land: String,
    val district: String,


)

fun jsonToModel(): MutableList<EstateModel> {
    val estatesList = mutableListOf<EstateModel>()
    val jsonArray = JSONTokener(Service.getJson()).nextValue() as JSONArray
    for (i in 0 until jsonArray.length()) {

        val id = jsonArray.getJSONObject(i).getString("id").toInt()
        val estateNo = jsonArray.getJSONObject(i).getString("estateNo")
        val area = jsonArray.getJSONObject(i).getString("area").toFloat()
        val roundedArea = String.format("%.2f", area)
        val land = jsonArray.getJSONObject(i).getString("land")
        val district = jsonArray.getJSONObject(i).getString("district")
        estatesList.add(i, EstateModel(id, estateNo, roundedArea, land, district))
    }
    return estatesList
}
