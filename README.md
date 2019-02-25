Neo4J Kotlin example
===
The goal of this project is to show a sample of implementation of a Kotlin application with Neo4J database.

### Tech stack
- [Gradle](https://gradle.org/)
- [Kotlin](https://kotlinlang.org/)
- [Javalin](https://javalin.io/)
- [Koin](https://insert-koin.io/)
- [Neo4J](https://neo4j.com/)
- [Neo4J-OGM](https://neo4j.com/docs/ogm-manual/current/) 
- [Spek Framework](https://spekframework.org/)

### Running

In order to get the project running, you need to:

* Provide the following environment vars:

```
SERVER_PORT	7000
NEO4J_URL	bolt://localhost:7687
NEO4J_USERNAME	neo4j
NEO4J_PASSWORD	password
```

* Have a running Neo4J instance that correspond to the configured vars. You can use Docker, for example:

```
$ docker container run --name neo4j -e NEO4J_AUTH=neo4j/password -p 7474:7474 -p 7687:7687 -d  neo4j
```

* Execute the application

```
$ gradle run
```

### About the project

#### Features

In order to test some flows of interaction with Neo4J, some features were implemented:

* **Create a User:** You can create a new user, by giving its username and its age:

`[POST] /users`
```json
{
  "username": "testUser",
  "age": 30
}
```

* **Add a friend to User:** You can add a new friend to user by giving the username of the friend:

`[POST] /users/<username>/friends`
```json
{
  "username": "friendUsername"
}
```

> To keep it simple, there is no verification if the username of the friend was already registered. So, if it doesn't, 
a new node is created.

* **Add a chat group:** You can add a user to a chat group by giving the chat name

`[POST] /users/<username>/chat-groups`
```json
{
  "name": "chat-name"
}
```  

* **Retrieve all direct contacts of a user:** You can retrieve all users that a particular user have direct contact. It 
means all users that shares a chat group with the given user.

`[GET] /users/<username>/direct-contacts`

#### Some implementation details

After implement the above flows, some insights must be highlighted:

**Dependencies**

To use Neo4J-OGM demands to declare a bunch of dependencies both to run the application and to test it, since they opted 
to avoid transitive dependencies. They are:

* **neo4j-ogm-core:** Provides the core implementation of OGM.
* **neo4j-ogm-bolt-driver:** The Java-Driver to connect to Neo4J via Bolt-protocol.
* **neo4j-ogm-embedded-driver:** The Java-Driver to interact with embedded Neo4J.
* **neo4j-kernel:** Provides the implementation of Neo4J embedded.
* **neo4j-lucene-index:** Supports smart indexing of numbers, querying for ranges and sorting such results, and so does 
its backend for Neo4j.
* **neo4j-cypher:** Provides support to Neo4J query language.
* **neo4j:** Neo4J community version.

**Domain models**

Once Neo4J-OGM is a Java based framework, it doesn't support some Kotlin features, like `data class` and immutable 
variables assigned with `val`. So, in order to keep these important features, mainly in domain models, we decided to 
not use OGM mapping in these classes, what demands to us implement mapped `DTOs` to interact with Neo4J-OGM, and 
implement routines to convert models into DTOs and vice versa. For example:

```kotlin
/* User.kt Domain model */
data class User(
        val username: String,
        val age: Int,
        val friends: List<User>,
        val chatGroups: List<ChatGroup>
)

/* UserNode.kt Neo4J-OGM DTO */
@NodeEntity("User")
class UserNode(
    @Id var username: String? = null,
    var age: Int? = 0,
    @Relationship(direction = Relationship.UNDIRECTED) var friends: List<UserNode> = emptyList(),
    @Relationship("BELONGS_TO") var chatGroups: List<ChatGroupNode> = emptyList()
) {
    fun toDomainModel(depth: Int = 1, currentDepth: Int = 0): User = User(
        this.username!!, this.age!!, this.friends.mapNotNull {
            if (currentDepth < depth) it.toDomainModel(depth, currentDepth + 1) else null
        }, this.chatGroups.map { it.toDomainModel(depth, currentDepth) }
    )
}

fun User.toDto(): UserNode = UserNode(
    this.username, this.age, this.friends.map { it.toDto() }
)
```

**Depth**

If you notice the `toDomainModel` method described in code example above, you may see the param `depth` that represents
a concern with how deep the method should parse the relationships of a given DTO. It is important in situations of 
undirected (bidirectional) relationships, what causes a `stackoverflow` error when try to parse these DTOs into Domain 
models.
