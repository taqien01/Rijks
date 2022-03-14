package id.reza.rijks.model


class User {
    var list: List<DataUser>? = null
}

class SatuUser {
    var username: String? = null
    var password: String? = null
}

data class DataUser(val username: String, val password: String){

}