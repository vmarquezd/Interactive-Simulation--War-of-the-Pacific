# Interactive Simulation: War of the Pacific

### Status: Archived (Completed 2023)
*This project was developed as a capstone for the IB Computer Science curriculum (Internal Assessment). It is preserved here to demonstrate proficiency in Java, Object-Oriented Programming, and API integration.*

## About
This project gamifies the history of the War of the Pacific. You control a naval vessel to explore a map of South America, battle enemy ships, and interact with historical figures to learn about the conflict.

It features a custom physics system for boat movement and integrates the OpenAI API to let players "chat" with historical NPCs in real-time.

## Key Features
* **AI Integration:** Chat with historical figures like Miguel Grau and Arturo Prat. The game sends your questions to GPT-3.5 to generate accurate historical answers.
* **Physics-Based Movement:** Custom vector math handles the boat's rotation, velocity, and momentum.
* **Combat System:** Shoot projectiles and dodge enemy that tracks and engages you.
* **Open World:** Explore locations like Lima, Santiago, and the Galapagos Islands.

## Built With
* **Java** (JavaFX for the GUI)
* **OpenAI API** (for NPC dialogue)
* **Maven**

## How to Run
1.  **Clone the repository.**
2.  **Open the project** in IntelliJ IDEA or Eclipse.
    * *Note: Open the folder containing the `pom.xml` file. If prompted, choose "Load as Maven Project".*
3.  **Reload Maven:**
    * In IntelliJ, look for the Maven icon (an "M") on the right sidebar and click the "Reload" (refresh) button. This downloads the required JavaFX libraries.
4.  **Add your API Key:**
    * Go to `src/main/java/com/example/demo/ChatGPTclient.java`.
    * Find the line: `new ChatGPTclient("Insert-Apikey")`.
    * Replace `"Insert-Apikey"` with your valid OpenAI API key.
5.  **Run the game:**
    * **Option A (Best):** Open the Maven sidebar, go to `Plugins` -> `javafx` -> `javafx:run` (double-click it).
    * **Option B:** Run the `Application.java` file directly.

---
*This project is archived for portfolio purposes.*
