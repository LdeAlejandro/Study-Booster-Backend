# 📘 API - Endpoints e Requisições

## 🧩 Entities
- `User`
- `Group`
- `ContentModule`
- `Subject`
- `Question`
- `Doc`

## 🧩 Relações Entre Entidades

- >`User`    * **---** *     `Group` → *Many-to-Many*
- >`Group`   * **---** * `Subject` → *Many-to-Many*
- >`Subject` **1---** * `ContentModule` → *One-to-Many*
- >`ContentModule`  * **---** * `Question` → *Many-to-Many*
- >`Question` * **---** **1** `QuestionOptions` → *Many-to-One*
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

- >`POST /create-subject`
- >`DELETE /delete-subject`

### Questions

- >`POST subject-ID/create-question` 
- >`PUT /subject-ID/edit-question/ID`
- >`DELETE /subject-ID/delete-question/ID`

---

### Docs

- >`POST subject-ID/create-doc` 
- >`PUT /subject-ID/edit-doc/ID`
- >`DELETE /subject-ID/delete-doc/ID`

---

## 🗂️ Grupos

- >`POST /create-group`
- >`POST /add-subject-to-group/{group-ID}`
- >`GET /list-group`
- >`POST /add-user-to-group/{group-ID}/{user-group}`

---

## 🧱 Módulos

- >`GET /subject-ID/modules`
- >`GET /subject-ID/module-ID`
- >`POST /subject-ID/module/add-question`
- >`POST /subject-ID/add-question-to-module`
- >`POST /subject-ID/add-doc-to-module`

---

## 🔗 Referências

- >`GET /subject-ID/doc-ID/reference-ID`


