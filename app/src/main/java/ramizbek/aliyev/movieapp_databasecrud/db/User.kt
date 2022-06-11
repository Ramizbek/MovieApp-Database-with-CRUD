package ramizbek.aliyev.movieapp_databasecrud.db

import java.io.Serializable

class User : Serializable {
    var id: Int? = null
    var name: String? = null
    var author: String? = null
    var about: String? = null
    var date: String? = null

    constructor(name: String?, author: String?, about: String?, date: String?) {
        this.name = name
        this.author = author
        this.about = about
        this.date = date
    }

    constructor()
    constructor(id: Int?, name: String?, author: String?, about: String?, date: String?) {
        this.id = id
        this.name = name
        this.author = author
        this.about = about
        this.date = date
    }


}