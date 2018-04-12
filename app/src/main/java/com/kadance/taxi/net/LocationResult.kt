package com.kadance.taxi.net

/**
 * Created by Kenza on 12.04.2018.
 *
 * http://json2java.azurewebsites.net/
 */


class LocationResult {

    var results: ArrayList<Result>? = null
    var status: String? = null


    class AddressComponent {
        var long_name: String? = null
        var short_name: String? = null
        var types: ArrayList<String>? = null
    }

    class Northeast {
        var lat: Double = 0.toDouble()
        var lng: Double = 0.toDouble()
    }

    class Southwest {
        var lat: Double = 0.toDouble()

        var lng: Double = 0.toDouble()
    }

    class Bounds {
        var northeast: Northeast? = null

        var southwest: Southwest? = null
    }

    class Location {
        var lat: Double = 0.toDouble()

        var lng: Double = 0.toDouble()
    }

    class Northeast2 {
        var lat: Double = 0.toDouble()

        var lng: Double = 0.toDouble()
    }

    class Southwest2 {
        var lat: Double = 0.toDouble()

        var lng: Double = 0.toDouble()
    }

    class Viewport {
        var northeast: Northeast2? = null

        var southwest: Southwest2? = null
    }

    class Geometry {
        var bounds: Bounds? = null

        var location: Location? = null

        var location_type: String? = null

        var viewport: Viewport? = null
    }

    class Result {
        var address_components: ArrayList<AddressComponent>? = null

        var formatted_address: String? = null

        var geometry: Geometry? = null

        var place_id: String? = null

        var types: ArrayList<String>? = null
    }
}
