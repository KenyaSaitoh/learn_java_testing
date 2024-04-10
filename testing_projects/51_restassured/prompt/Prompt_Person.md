以下の［OpenAPI仕様ファイル］に示したREST APIがあるものとします。
［制約条件］に従い、テストコードを生成してください。

# 制約条件

* テスティングフレームワークには、JUnit5とRestAssuredを利用してください。
* テストクラスは`pro.kensait.spring.person.rest.test.PersonApiTest`とします。
* REST APIの呼び出し結果は`io.restassured.response.Response`として取得し、
  レスポンスボディはJUnitのアサーションAPIで検証するものとします。
* テストメソッド名は英語で、コメントは日本語でお願いします。
* すべてのテストメソッドを生成してください。

# OpenAPI仕様ファイル

```
openapi: 3.0.0
info:
  title: Person API
  version: 1.0.0
  description: API for managing person data in a system.
servers:
  - url: http://localhost:8080
    description: Development server

paths:
  /persons/{personId}:
    get:
      summary: Get a person by ID
      operationId: getPersonById
      tags:
        - Persons
      parameters:
        - name: personId
          in: path
          required: true
          description: Unique identifier of the person
          schema:
            type: integer
      responses:
        '200':
          description: A person object
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Person'
        '404':
          description: Person not found
    put:
      summary: Replace a person
      operationId: replacePerson
      tags:
        - Persons
      parameters:
        - name: personId
          in: path
          required: true
          description: Unique identifier of the person
          schema:
            type: integer
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/Person'
      responses:
        '200':
          description: Person replaced
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Person'
        '400':
          description: Invalid input
    delete:
      summary: Delete a person
      operationId: deletePerson
      tags:
        - Persons
      parameters:
        - name: personId
          in: path
          required: true
          description: Unique identifier of the person
          schema:
            type: integer
      responses:
        '200':
          description: Person deleted
          content:
            application/json:
              schema:
                type: integer

  /persons:
    get:
      summary: Get all persons
      operationId: getAllPersons
      tags:
        - Persons
      responses:
        '200':
          description: An array of persons
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Person'

    post:
      summary: Create a new person
      operationId: createPerson
      tags:
        - Persons
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/Person'
      responses:
        '200':
          description: New person created
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Person'
        '400':
          description: Invalid input

  /persons/query_by_age:
    get:
      summary: Query persons by lower age limit
      operationId: queryByLowerAge
      tags:
        - Persons
      parameters:
        - name: lowerAge
          in: query
          required: true
          description: Lower age limit for the query
          schema:
            type: integer
      responses:
        '200':
          description: An array of persons matching the criteria
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Person'

components:
  schemas:
    Person:
      type: object
      properties:
        personId:
          type: integer
        personName:
          type: string
        age:
          type: integer
        gender:
          type: string
      required:
        - personName
        - age
        - gender
```