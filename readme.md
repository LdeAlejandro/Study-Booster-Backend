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

- **Update Subject**                      
   `PUT /subjects/{subjectCurrentName}` 
    ```json
  {
     "subjectName": "TEST UPDATED"
  }

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

- **Create SubModule**                      
  `POST /subject/{subjectId}/module`
    ```json
  {
     "name": "YOUR MODULE NAME"
     "parentId": 1
  }
  
- >`PUT /subject/{subjectId}/module/{moduleId}`
- >`DELETE /subject/{subjectId}/module/{moduleId}`

---
### Questions
- >`GET /module/{moduleId}/question`
- >`GET /module/{moduleId}/question/{questionId}`
- >`POST /module/{moduleId}/question`
- >`PUT /module/{moduleId}/question/{id}`
- >`DELETE /module/{moduleId}/question/{id}`
  
---

### Questions Options

- >`GET /question/{questionId}/option`
- >`POST /question/{questionId}/option`
- >`PUT /question/{questionId}/option/{optionId}`
- >`DELETE /question/{questionId}/option/{optionId}`


---

### Docs
- >`GET /module/{moduleId}/docs`
- >`GET /module/{moduleId}/docs/{docId}`
- >`POST /module/{moduleId}/docs`
- >`PUT /module/{moduleId}/docs/{docId}`
- >`DELETE /module/{moduleId}/docs/{docId}`
---

## 🗂️ Groups

- >`POST /group/create-group`
- >`POST /group/add-subject-to-group/{id}`
- >`GET /group/list-group`
- >`POST /group/add-user-to-group/{id}/{user-group}`
  
---

## 🔗 Reference

- >`GET /module/{id}/doc/{id}/reference/{id}`


