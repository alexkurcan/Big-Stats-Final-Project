# Big Stats: Study For The AP Stats Exam!
## By Sham Nemer and Alex Kurcan

## How to play?
Navigate to the green "<> Code" button, click on it. Hit "Local" and look for "Download ZIP" After this unzip the file and open it within and IDE. Usually VS Code
should work. If you are doing it through VS Code follow these steps:
1. Hit "Open..." with the folder icon
2. Locate the downloaded unzipped folders "Big Stats"
3. Navigate to the "main" folder and look for "Main.java" run that file
4. And Enjoy!

### The Bosses

| # | Boss | Unit | Difficulty |
|---|------|------|------------|
| 1 | The Histogram Hydra | Exploring One-Variable Data | Easy |
| 2 | The Scatterplot Spider | Exploring Two-Variable Data | Easy |
| 3 | The Survey Phantom | Collecting Data | Medium |
| 4 | The Probability Golem | Probability & Random Variables | Medium |
| 5 | The CLT Titan | Sampling Distributions | Hard |
| 6 | The Chi-Square Dragon | Inference for Categorical Data | Hard |
| ★ | **THE AP EXAM** | All units, random order | Final Boss |

```
BigStats2/
├── pom.xml

└── src/
    ├── main/java/bigstats/
    │   ├── Main.java                        # Entry point, defines boss list
    │   ├── controller/
    │   │   └── GameController.java          # Main game loop
    │   ├── model/
    │   │   ├── Boss.java                    # Boss stats & damage
    │   │   ├── Player.java                  # Player HP, score, accuracy
    │   │   ├── Question.java                # Single MCQ
    │   │   ├── QuestionBank.java            # Stores all questions by unit
    │   │   ├── QuestionFactory.java         # All 60+ AP Stats questions
    │   │   ├── QuestionIterator.java        # Iterates questions for a fight
    │   │   └── GameState.java               # Tracks stage & boss progression
    │   ├── pattern/
    │   │   ├── AbstractBossFight.java       # Shared fight logic
    │   │   ├── UnitFight.java               # Single-unit boss fight
    │   │   └── APExamFight.java             # Final exam (all units, 75% threshold)
    │   └── view/
    │       ├── GameView.java                # View interface
    │       └── ConsoleView.java             # Terminal UI with ASCII art
    └── test/java/bigstats/
        ├── BossTest.java
        ├── PlayerTest.java
        ├── GameStateTest.java
        ├── QuestionTest.java
        ├── QuestionBankTest.java
        ├── QuestionFactoryTest.java
        ├── QuestionIteratorTest.java
        └── APExamFightTest.java
```
# Coverage section
We had about an 70% coverage rate.
![alt text](https://github.com/alexkurcan/Big-Stats-Final-Project/blob/main/image_2026-04-29_100349532.png "Coverage Screenshot from Alex's Computer")
