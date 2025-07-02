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

## 📚 Subjects, Questions e Docs

### Subjects

- >`GET /subjects?page=0&size=10`
- >`POST /subjects`
- >`PUT /subjects/{subjectCurrentName}`
- >`DELETE /subjects/{subjectName}`

---

## 🧱 Modules

- >`GET /subject/{subjectId}/module/get-page-modules?page=0&size=10`
- >`GET /subject/{subjectId}/module/{moduleId}`
- >`GET /subject/{subjectId}/module/depth?depth=2`
- >`POST /subject/{subjectId}/module`
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
- >`POST /module/{id}/doc/create-doc`
- >`PUT /module/{id}/doc/edit-doc/{id}`
- >`DELETE /module/{id}/doc/delete-doc/{id}`
---

## 🗂️ Groups

- >`POST /group/create-group`
- >`POST /group/add-subject-to-group/{id}`
- >`GET /group/list-group`
- >`POST /group/add-user-to-group/{id}/{user-group}`
  
---

## 🔗 Reference

- >`GET /module/{id}/doc/{id}/reference/{id}`


