package ramizbek.aliyev.movieapp_databasecrud.db

interface MyDBInterface {
    fun createUser(user: User)
    fun readUser():ArrayList<User>
    fun updateUser(user: User):Int
    fun deleteUser(user: User)
}