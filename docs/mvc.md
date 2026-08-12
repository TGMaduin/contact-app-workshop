# MVC Design Pattern

## What is MVC?

MVC (Model-View-Controller) is a software architecture pattern that separates an application into three different responsibilities:

- **Model** – Represents the application's data and business rules.
- **View** – Handles user interaction and presentation.
- **Controller** – Coordinates communication between the View and the Model.

This separation makes the code easier to understand, maintain, test, and extend.

---

## MVC in this Contact Application

### Model

The Model contains the application's data and validation logic.

**Classes**

- `Contact`

**Responsibilities**

- Store contact information.
- Validate input data.
- Throw `IllegalArgumentException` when invalid data is provided.

The Model has no knowledge of the user interface or data storage.

---

### Data Layer (DAO)

The Data Access Object (DAO) layer is responsible for persistence.

**Classes**

- `ContactDAO`
- `FileContactDAOImpl`

**Responsibilities**

- Read contacts from a text file.
- Save contacts to a text file.
- Search for contacts.
- Throw `ContactStorageException` or `DuplicateContactException` when necessary.

The DAO never communicates directly with the user and never prints to the console.

---

### View

The View is responsible for all interaction with the user.

**Class**

- `ContactView`

**Responsibilities**

- Display menus.
- Display contacts.
- Display success and error messages.
- Read user input using Java's `IO` class.

The View is the only layer that interacts directly with the console.

---

### Controller

The Controller coordinates the application.

**Class**

- `ContactController`

**Responsibilities**

- Receive user input from the View.
- Call the Model and DAO.
- Handle the application flow.
- Catch exceptions and forward them to the `ExceptionHandler`.
- Decide what information should be displayed to the user.

The Controller contains the application's main loop but performs no input/output itself.

---

### Exception Handling

Exception handling is centralized through the `ExceptionHandler` class.

The Controller catches exceptions thrown by the Model or DAO and delegates them to the ExceptionHandler, which decides which error message should be presented to the user.

This keeps the Controller focused on application flow while keeping error handling in a single location.

---

## Benefits of MVC

Using the MVC pattern provides several advantages:

- Separation of responsibilities.
- Easier testing.
- Better maintainability.
- Improved readability.
- Components can be modified independently.

For example, this project originally used `Scanner` and `System.out` for console input and output. After switching to Java's new `IO` API, only the `ContactView` class needed to be updated. The Model, DAO, Controller, and ExceptionHandler remained unchanged because the user interface is isolated within the View layer.

This demonstrates one of the main benefits of MVC: changes in one layer have minimal impact on the rest of the application.