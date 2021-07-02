package com.flicker.sampleapp.network.model

import com.flicker.sampleapp.domain.model.Photo
import com.flicker.sampleapp.domain.util.DomainMapper

class PhotoDtoMapper: DomainMapper<PhotoDto, Photo> {

    override fun mapToDomainModel(model: PhotoDto): Photo {
        return Photo(
            id = model.id,
            title = model.title,
            owner = model.owner,
            secret = model.secret,
            server = model.server,
            farm = model.farm,
            isPublic = model.isPublic,
            isFamily = model.isFamily,
            isFriend = model.isFriend,
            photoUrl = "https://farm${model.farm}.staticflickr.com/${model.server}/${model.id}_${model.secret}_m.jpg"
        )
    }

    override fun mapFromDomainModel(domainModel: Photo): PhotoDto {
        return PhotoDto(
            id = domainModel.id,
            title = domainModel.title,
            owner = domainModel.owner,
            secret = domainModel.secret,
            server = domainModel.server,
            farm = domainModel.farm,
            isPublic = domainModel.isPublic,
            isFriend = domainModel.isFriend,
            isFamily = domainModel.isFamily,
        )
    }

    fun toDomainList(initial: List<PhotoDto>) : List<Photo> {
       return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Photo>) : List<PhotoDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}