### **README.md**

# **KValidation**

KValidation is a powerful and flexible validation framework for Kotlin, designed to be easy to integrate with **Jetpack Compose** or other frameworks. It provides a simple **DSL**, supports automatic validation via **annotations**, and includes predefined rules for common validation needs, saving you development time.

---

## **Key Features**

- **Rich DSL**: Easily define validation rules using a declarative syntax.
- **Annotation-Based Validation**: Automate validation by annotating your data models.
- **Predefined Rules**: Includes common validation rules like email, password, phone number, etc.
- **Custom Validation**: Create your own validation logic for specific use cases.
- **Jetpack Compose Integration**: Ready-to-use components like `ValidatedTextField` and `ValidatedForm`.

---

## **Installation**

### **Gradle**

UPDATE SOON


---

## **Getting Started**

### **1. Define Validation Rules with DSL**
```kotlin
val validationRules = ValidationRules().apply {
    field("email") {
        notEmpty("Email is required")
        pattern(
            regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}",
            message = "Invalid email format"
        )
    }
    field("password") {
        notEmpty("Password is required")
        pattern(".{8,}", "Password must be at least 8 characters")
        pattern(".*[!@#\$%^&*()].*", "Password must contain a special character")
    }
}
```

### **2. Validate Data**
```kotlin
val inputData = mapOf(
    "email" to "user@example.com",
    "password" to "P@ssw0rd"
)

val errors = validationRules.validate(inputData)

if (errors.isEmpty()) {
    println("All inputs are valid!")
} else {
    errors.forEach { println(it) }
}
```

---

### **3. Automatic Validation with Annotations**

#### **Define a Data Model**
```kotlin
data class User(
    @NotEmpty("Email is required")
    @Pattern("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", "Invalid email format")
    val email: String,

    @NotEmpty("Password is required")
    @MinLength(8, "Password must be at least 8 characters")
    val password: String
)
```

#### **Validate the Model**
```kotlin
val user = User(email = "", password = "123")
val errors = validateModel(user)

if (errors.isEmpty()) {
    println("Model is valid!")
} else {
    errors.forEach { println(it) }
}
```

---

### **4. Use Predefined Rules**
KValidation provides predefined rules for common scenarios:
```kotlin
val predefinedRules = ValidationRules().apply {
    field("email", PredefinedRules.email())
    field("password", PredefinedRules.password(minLength = 6))
}
```

---

### **5. Jetpack Compose Integration**
```kotlin
@Composable
fun RegistrationForm() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val validationRules = ValidationRules().apply {
        field("email", PredefinedRules.email())
        field("password", PredefinedRules.password(minLength = 8))
    }

    val errors = validationRules.validate(
        mapOf("email" to email, "password" to password)
    )

    Column {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Button(onClick = {
            if (errors.isEmpty()) println("Form is valid!") else println(errors.joinToString("\n"))
        }) {
            Text("Submit")
        }

        errors.forEach { error ->
            Text(error, color = Color.Red)
        }
    }
}
```

---

## **Extending the Framework**

### **Add Custom Validation**
```kotlin
val customRules = ValidationRules().apply {
    field("customField") {
        custom("Value must be even") { value ->
            value?.toIntOrNull()?.let { it % 2 == 0 } ?: false
        }
    }
}
```

### **Add New Predefined Rules**
You can add your own predefined rules to the `predefined/` folder. For example:
```kotlin
object PredefinedRules {
    fun url() = FieldValidation().apply {
        notEmpty("URL is required")
        pattern("^(https?|ftp)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}.*$", "Invalid URL format")
    }
}
```

---

## **Project Structure**
UPDATE SOON

---

## **Contributing**
We welcome contributions from the community! Feel free to open a pull request or report issues in the repository.

---

## **License**
KValidation is distributed under the MIT License. See the `LICENSE` file for details.
