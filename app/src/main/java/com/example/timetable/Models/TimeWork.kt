package com.example.timetable.Models

class TimeWork{
    var id: Int? = null
    var date: String? = null
    var name: String? = null
    var from: String? = null
    var until: String? = null
    var type: Int? = null
    var pagerType: Int? = null

    constructor()

    constructor(
        id: Int?,
        date: String?,
        name: String?,
        from: String?,
        until: String?,
        type: Int?,
        pagerType: Int?
    ) {
        this.id = id
        this.date = date
        this.name = name
        this.from = from
        this.until = until
        this.type = type
        this.pagerType = pagerType
    }

    constructor(
        date: String?,
        name: String?,
        from: String?,
        until: String?,
        type: Int?,
        pagerType: Int?
    ) {
        this.date = date
        this.name = name
        this.from = from
        this.until = until
        this.type = type
        this.pagerType = pagerType
    }

}