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

- >`GET /subjects`
- >`POST /create-subject`
- >`PUT /edit-subject/{subjectCurrentName}`
- >`DELETE /delete-subject/{subjectCurrentName`

---

## 🧱 Modules

- >`GET /subject/{id}/module`
- >`GET /subject/{id}/module/{moduleId}`
- >`GET /subject/{id}/modules?depth={depth}`
- >`POST /subject/{id}/module`
- >`POST /subject/{id}/module/{moduleId}/add-question`
- >`PUT /subject/{id}/module/{moduleId}`
- >`DELETE /subject/{id}/module/{moduleId}`

---
### Questions
- >`GET /module/{moduleId}/question/module-questions`
- >`GET /module/{moduleId}/question/{questionId}`
- >`POST /module/{moduleId}/question/create-question`
- >`PUT /module/{moduleId}/question/edit-question/{questionId}`
- >`DELETE /module/{moduleId}/question/delete-question/{questionId}`
  
---

### Questions Options

- >`GET /question/{id}/option/answer-options`
- >`POST /question/{id}/option/create-option`
- >`PUT /question/{id}/option/edit-options`
- >`DELETE /question/{id}/option/delete-option/{optionId}`


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


