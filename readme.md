# 📘 API - Endpoints e Requisições

## 🧩 Entities
- `User`
- `Group`
- `ContentModule`
- `Subject`
- `Question`
-`QuestionOptions`
- `Doc`

## 🧩 Relações Entre Entidades

- >`User`    * **---** *     `Group` → *Many-to-Many*
- >`Group`   * **---** * `Subject` → *Many-to-Many*
- >`Subject` **1---** * `ContentModule` → *One-to-Many*
- >`ContentModule`  * **---** * `Question` → *Many-to-Many*
- >`ContentModule`  * **---** 1 `ContentModule` → *Many-to-One*
- >`ContentModule`  1 **---** * `ContentModule` → *One-to-Many*
- >`Question` * **---** **1** `QuestionOptions` → *One-to-Many*
- >`ContentModule` * **---** * `Doc` → *Many-to-Many*

---

## 🧩 Autenticação e Usuário

- >`POST /admin/create-user`
- >`POST /create-user`
- >`POST /login`
- >`POST /logout`
- >`GET /verify`
- >`POST /reset-password`

---

## 📚 Subjects, ContentModule, Questions e Docs endpoints

### Subjects

- **List Subjects page**  
  `GET /subjects?page=0&size=10`


- **Create Subject**  
  `POST /subjects`
  ```json
  {
    "subjectName": "TEST2"
  }
  ```

- **Update Subject**                      
   `PUT /subjects/{subjectCurrentName}` 
    ```json
  {
     "subjectName": "TEST UPDATED"
  }
  ```

- **Delete Subject**      
    `DELETE /subjects/{subjectName}`

---

## 🧱 Modules

- **Get All Modules Pageable**   
`GET /subject/{subjectId}/module/get-page-modules?page=0&size=10`


- **Get Modules By Depth**   
    `GET /subject/3/module/depth?depth=1&page=0&size=10`


- **Get Module By Id**  
`GET /subject/{subjectId}/module/{moduleId}`


- **Create Module**                      
  `POST /subject/{subjectId}/module`
    ```json
  {
     "name": "YOUR MODULE NAME"
  }
  ```

- **Create SubModule**                      
  `POST /subject/{subjectId}/module`
    ```json
  {
     "name": "YOUR MODULE NAME"
     "parentId": 1
  }
  ```

- **Update Module Name By ID**                      
`PATCH /subject/{subjectId}/module/{moduleId}`                 
    ```json
    {  
      "name": "YOUR NEW MODULE NAME"
    }
    ```

- **Delete Module By ID**
- >`DELETE /subject/{subjectId}/module/{moduleId}`

---
### Questions
- >`GET /module/{moduleId}/question`
- >`GET /module/{moduleId}/question/{questionId}`
- **Get Questions Pageable**                       
  `POST /subject/3/module/3/question?page=1&size=60`


- **Post Question**                       
  `POST /subject/{subjectId}/module/{moduleId}/question`
    ```json
    {
        "question": "What is the capital of France2?",
        "answerExplanation": "Paris is the capital city of France.",
        "options": [
            { "option": "Paris", "correct": true },
            { "option": "London", "correct": false },
            { "option": "Berlin", "correct": false },
            { "option": "Madrid", "correct": false }
        ]
    }
    ```

- **Put Question**                        
  `PUT /question/{questionId}`
    ```json
    {
        "question": "What is the capital of France2?",
        "answerExplanation": "Paris is the capital city of France.",
        "options": [
            { "option": "Paris", "correct": true },
            { "option": "London", "correct": false },
            { "option": "Berlin", "correct": false },
            { "option": "Madrid", "correct": false }
        ]
    }
    ```
- **Delete Question**  
`DELETE /question/{id}`

---

### Docs

- **Get Doc by ID**  
`GET /module/{moduleId}/doc/{docId}`


- **Create Doc**  
`POST /module/{moduleId}/doc`
  ```json
  {
    "title": "Markdown Introduction",
    "content": "# LOREM CONTENT"
  }
  ```

- **Update Doc By Id**  
  `PUT /module/{moduleId}/doc/{docId}`
  ```json
  {
    "title": "Markdown Introduction",
    "content": "# LOREM CONTENT"
  }
  ```

- **Delete Doc By Id**   
`DELETE /module/{moduleId}/docs/{docId}`
---
### Random-question

- **Get Random Question from module id**  
  `GET /preferences/{moduleId}/random-question`
---

### Alert Preferences

- **Get current preferences from DB**  
  `GET /preferences`


- **Create Preferences**  
  `POST /preferences`
  ```json
  {
    "label": "Estudo Java",
    "interval": "MIN_15",
    "subjectId": 3,
    "moduleId": null
  }
  ```

- **Update Preferences by ID**  
  `PUT /preferences`
  ```json
  {
    "id": 1,
    "label": "Estudo atualizado",
    "interval": "MIN_30",
    "subjectId": 3,
    "moduleId": null
  }
  ```

  - **Delete preferences By Id**   
    `DELETE /preferences/{id}`
---

PENDING: 
## 🗂️ Groups

- >`POST /group/create-group`
- >`POST /group/add-subject-to-group/{id}`
- >`GET /group/list-group`
- >`POST /group/add-user-to-group/{id}/{user-group}`

## Authentication
---


