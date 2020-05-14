package ss.multiActivity

import android.os.Bundle

data class User(
    val userName: String,
    val firstName: String,
    val lastName: String
) {

    fun toBundle(): Bundle {
        val b = Bundle()
        b.putString("userName", userName)
        b.putString(::userName.name, userName)
        b.putString(::firstName.name, firstName)
        b.putString(::lastName.name, lastName)
        return b
    }

    companion object {
        fun fromBundle(b: Bundle?): User {

            return User(
                userName = b?.getString(User::userName.name)
                    ?: "",
                firstName = b?.getString(User::firstName.name)
                    ?: "",
                lastName = b?.getString(User::lastName.name)
                    ?: ""
            )
        }
    }
}