# **Repository Overview**

## **Table of Contents**
1. [Introduction](#introduction)
2. [Project Structure](#project-structure)
3. [Projects Overview](#projects-overview)
    - [Array Manipulation and Missing Number](#array-manipulation-and-missing-number)
    - [Product Inventory Management](#product-inventory-management)
    - [Currency Converter](#currency-converter)

---

## **Introduction**
This repository contains 3 projects for mobile intern project assignment at TymeX, each project is organized into its own folder.

---

## **Project Structure**
The repository is divided into the following three main folders, each representing a separate project:
- **Array_Manipulation_And_Missing_Number**
- **Product_Inventory_Management**
- **Currency_Converter**

---

## **Projects Overview**

### **1. Currency Converter App**
**Description:**  
An android mobile app using Kotlin and Jetpack Compose to convert currency base on rates provided by api

### **Video demo**
[![demo](https://markdown-videos-api.jorgenkh.no/https://drive.google.com/file/d/1R4GtpUF9Ktq0GJLDTiBbUAUXfl8DfE-N/view?usp=sharing)](https://drive.google.com/file/d/1R4GtpUF9Ktq0GJLDTiBbUAUXfl8DfE-N/view?usp=sharing)

# Note About the Project

This project utilizes the **ExchangeRates API** for fetching currency exchange rates and available currency symbols. Due to the limitations of the **free plan** of the API, the base currency is fixed to **EUR** (Euro). 

As a result, the app is only capable of performing currency conversions with EUR as the base currency. Users cannot change the base currency to other options unless the API plan is upgraded to a higher tier that supports custom base currencies.

This constraint is reflected throughout the app's design and functionality to ensure a seamless experience while working within the API limitations.


#### **How to Run**  
1. Clone the repository:
   ```bash
   git clone https://github.com/DinhHoangPhuc/tymex_project_assignment_mobile_intern.git
   cd Array_Manipulation_And_Missing_Number
2. Open the project in **Android Studio**.
3. Sync the project with **Gradle files**
4. Build the project using **Gradle Kotlin DSL**(it might take a while).
5. Run the project.

#### **Technical Details**  
- **Language:** Kotlin
- **UI framework:** Jetpack Compose
- **API intergration:** Retrofit
- **Asynchronous programming:** Coroutines
- **IDE:** Android Studio Jellyfish
- **Build System:** Gradle Kotlin DSL

#### **Project structure**
![demo](https://github.com/DinhHoangPhuc/tymex_project_assignment_mobile_intern/blob/main/photo/project_currency_converter.png)

### `manifests`
- **`AndroidManifest.xml:`** Defines essential information about the app such as the package name, permissions, and entry points.

### `data`
- **`CurrencyApi.kt:`** Defines the Retrofit interface for API endpoints related to currency conversion and symbol fetching. Includes functions for network requests like fetching currency rates or available symbols.
  
- **`CurrencyRepository.kt:`** Acts as the single source of truth for currency data. Handles data operations by interacting with `CurrencyApi` and managing API responses.

- **`CurrencyResponse.kt:`** A data class representing the structure of the JSON response from the currency API for conversion rates.

- **`LoggingInterceptor.kt:`** Provides a `HttpLoggingInterceptor` for logging API request and response data, useful for debugging network calls.

- **`RetrofitInstance.kt:`** Configures and provides the `Retrofit` instance for making network requests. Includes setup for base URL, logging interceptor, and other configurations.

- **`SymbolsResponse.kt:`** A data class for modeling the API response containing available currency symbols.

### `ui.theme`
- This folder typically contains files for Jetpack Compose theming, such as managing app colors, typography, and shapes to maintain consistent styling across the app.

### `view_model`
- **`CurrencyViewModel.kt:`** Implements the `ViewModel` to manage UI-related data for the currency converter. It handles business logic, interacts with the repository, and exposes LiveData for the UI to observe.

### `MainActivity.kt`
- The entry point of the app, hosting the main UI composables. Sets up Jetpack Compose content and integrates the `CurrencyViewModel`.

## `res`
- Contains resources such as layouts, drawables, strings, and other assets used in the app.

## Gradle Scripts
- **`build.gradle.kts (Project):`** Manages dependencies, repositories, and configurations for the entire project.
  
- **`build.gradle.kts (Module: app):`** Configures app-specific dependencies, plugins, and build settings.

- **`proguard-rules.pro:`** Specifies rules for code obfuscation and shrinking in release builds.

- **`gradle.properties:`** Contains configuration properties for the Gradle build system.

- **`gradle-wrapper.properties:`** Specifies the version of the Gradle wrapper used to build the project.

- **`libs.versions.toml:`** A centralized version catalog for managing dependencies and their versions.

- **`local.properties:`** Contains local configuration settings like the Android SDK location.

---
  
#### **Example Output**
When running the program, you might see the following output:
![demo](https://github.com/DinhHoangPhuc/tymex_project_assignment_mobile_intern/blob/main/photo/demo_cunrrency_converter.png)

### **2. Array Manipulation and Missing Number**
**Description:**  
A Kotlin-based console project, this project implement algorithm to find missing numbers in sequences.  

#### **How to Run**  
1. Clone the repository:
   ```bash
   git clone https://github.com/DinhHoangPhuc/tymex_project_assignment_mobile_intern.git
   cd Array_Manipulation_And_Missing_Number
2. Open the project in **IntelliJ IDEA**.
3. Build the project using **Gradle Kotlin DSL**.
4. Locate and run the `main` function in the `Main.kt` file to execute the program.

#### **Technical Details**  
- **Language:** Kotlin
- **IDE:** IntelliJ IDEA
- **Build System:** Gradle Kotlin DSL

#### **Project structure**
![demo](https://github.com/DinhHoangPhuc/tymex_project_assignment_mobile_intern/blob/main/photo/project_structure_missing_number.png)
- `MissingNumberHelper.kt:` contains function to find missing number
- `Main.kt:` contains `main` function to run the project
  
#### **Example Output**
When running the program, you might see the following output:
![demo](https://github.com/DinhHoangPhuc/tymex_project_assignment_mobile_intern/blob/main/photo/demo_missing_number.png)
  
---

### **3. Product Inventory Management**
**Description:**  
A Kotlin-based console project to manage product inventories.

#### **How to Run**
1. Clone the repository:
   ```bash
   git clone https://github.com/DinhHoangPhuc/tymex_project_assignment_mobile_intern.git
   cd Product_Inventory_Management
2. Open the project in **IntelliJ IDEA**.
3. Build the project using **Gradle Kotlin DSL**.
4. Locate and run the `main` function in the `Main.kt` file to execute the program.

#### **Features**
- Calculate the total value of all products in inventory.
- Identify the most expensive product.
- Check stock availability for specific item.
- Sort products by price or quantity in ascending or descending order.

#### **Technical Details**
- **Language:** Kotlin  
- **IDE:** IntelliJ IDEA  
- **Build System:** Gradle Kotlin DSL  

#### **Project structure**
![demo](https://github.com/DinhHoangPhuc/tymex_project_assignment_mobile_intern/blob/main/photo/project_structure_inventory.png)
- `InventoryHelper.kt:` contains functions to perform features
- `InventoryItem.kt:` data class
- `Main.kt:` contains `main` function to run the project
  
#### **Example Output**
When running the program, you might see the following output:
![demo](https://github.com/DinhHoangPhuc/tymex_project_assignment_mobile_intern/blob/main/photo/demo_product_inventory.png)
