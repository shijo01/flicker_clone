package com.flicker.sampleapp.presentation.ui.photo_list

enum class PhotoCategory(val value: String) {
    NATURE("Nature"),
    DOGS("Dogs"),
    CATS("Cats"),
    COMPUTER("Computer"),
    LANDSCAPE("Landscape"),
    BIRDS("Birds"),
    CARS("Cars"),
    PIZZA("Pizza"),
    MOUNTAINS("Mountains"),
}

fun getAllFoodCategory(): List<PhotoCategory> {
    return listOf(
        PhotoCategory.NATURE, PhotoCategory.DOGS,
        PhotoCategory.BIRDS, PhotoCategory.CATS, PhotoCategory.COMPUTER,
        PhotoCategory.LANDSCAPE, PhotoCategory.CARS,
        PhotoCategory.PIZZA, PhotoCategory.MOUNTAINS
    )
}

fun getFoodCategory(value: String): PhotoCategory? {
    val map = PhotoCategory.values().associateBy(PhotoCategory::value)
    return map[value]
}