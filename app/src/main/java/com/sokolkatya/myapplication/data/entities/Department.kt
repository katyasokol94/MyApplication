package com.sokolkatya.myapplication.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Department {

    @SerialName("Acting")
    ACTING,

    @SerialName("Art")
    ART,

    @SerialName("Camera")
    CAMERA,

    @SerialName("Costume & Make-Up")
    COSTUME_MAKE_UP,

    @SerialName("Creator")
    CREATOR,

    @SerialName("Crew")
    CREW,

    @SerialName("Directing")
    DIRECTING,

    @SerialName("Editing")
    EDITING,

    @SerialName("Lighting")
    LIGHTING,

    @SerialName("Production")
    PRODUCTION,

    @SerialName("Sound")
    SOUND,

    @SerialName("Visual Effects")
    VISUAL_EFFECT,

    @SerialName("Writing")
    WRITING;
}
