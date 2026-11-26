# NavigationLab — лабораторна №2

Kotlin, Jetpack Compose, MVVM, StateFlow і Navigation Compose.
Ця гілка продовжує лабораторну №1; її початковий коміт збережений у master.

## Приклад викладача

Перевірено репозиторій https://github.com/oboris/NewNav2025
на коміті 5b7c2511ab938bdd5b168fe0dd73fe5962842234.

- [FavoriteScreen.kt](https://github.com/oboris/NewNav2025/blob/5b7c2511ab938bdd5b168fe0dd73fe5962842234/app/src/main/java/ua/edu/cdu/boris/newnav2025/ui/screens/favorite/FavoriteScreen.kt):
  sealed class маршрутів, NavHost, composable, navigate.
- [MainActivity.kt](https://github.com/oboris/NewNav2025/blob/5b7c2511ab938bdd5b168fe0dd73fe5962842234/app/src/main/java/ua/edu/cdu/boris/newnav2025/MainActivity.kt):
  rememberNavController, currentBackStackEntryAsState, navigateUp і кнопка «Назад».
- [libs.versions.toml](https://github.com/oboris/NewNav2025/blob/5b7c2511ab938bdd5b168fe0dd73fe5962842234/gradle/libs.versions.toml):
  Navigation Compose 2.9.6. Використано ту саму версію.

З прикладу адаптовано спосіб організації навігації, а розмітка, назви
маршрутів та предметні дані залишаються власними.

## Пункт 0: способи навігації

1. Перемикання Compose-функцій через when і стан: підходить для простих
   вкладок, але саме по собі не створює історію переходів.
2. Navigation Compose: NavController керує переходами, NavHost відображає
   поточний маршрут, composable описує підекрани. Є стек повернення.
3. Navigation 3: окремий підхід Compose із явним керуванням back stack.
   У цій роботі обрано Navigation Compose 2, щоб відповідати прикладу викладача.

Джерела:
- https://developer.android.com/guide/navigation
- https://developer.android.com/guide/navigation/backstack
- https://developer.android.com/guide/navigation/navigation-3

## Що реалізовано

У вкладці «Лічильник» є три підекрани:
1. Лічильник: розмітка з лабораторної №1, кнопка +1 і додана кнопка «Відкрити статистику».
2. Статистика: поточна кількість натискань, кнопки «Відкрити довідку» і «Назад до лічильника».
3. Довідка: пояснення, повернення до статистики або одразу на початок.

Два переходи вперед: Лічильник → Статистика → Довідка.
Системна кнопка/жест «Назад» працює через стек NavHost.
Кнопка зверху використовує navigateUp(), кнопки всередині — popBackStack().
Повернення на початок видаляє підекрани зі стеку, а не створює новий лічильник.
Вкладки «Повідомлення» і «Бали» збережено.

## Структура

- MainActivity.kt: отримання LabViewModel, тема, запуск NavigationLabApp.
- model/LabState.kt: незмінні дані стану.
- viewmodel/LabViewModel.kt: StateFlow і методи зміни даних.
- navigation/AppDestinations.kt: вкладки.
- navigation/CounterNavigationGraph.kt: маршрути трьох підекранів.
- ui/NavigationLabApp.kt: навігаційна панель, заголовок, кнопка повернення.
- ui/screens/CounterNavigationScreen.kt: NavHost з трьома composable.
- ui/screens/CounterScreen.kt: лічильник з попередньої роботи.
- ui/screens/CounterDetailsScreen.kt і CounterHelpScreen.kt: нові підекрани.

NavController не зберігається у ViewModel: переходами керує UI,
а лічильником, повідомленням і балами — ViewModel.
Дані ViewModel переживають звичайний поворот Activity, але не зберігаються
постійно після завершення процесу.

## Як запустити

Відкрити корінь проєкту в Android Studio, виконати Gradle Sync, обрати
емулятор/телефон з Android 9 (API 28) або новішим і натиснути Run.

Збережено конфігурацію початкового проєкту:
AGP 9.5.1, Gradle 9.6.0, compileSdk/targetSdk 37, Gradle JVM 25.
Додано Lifecycle 2.10.0 та Navigation Compose 2.9.6 через каталог версій.

Команди перевірки:
```sh
bash gradlew :app:assembleDebug :app:testDebugUnitTest :app:lintDebug
bash gradlew :app:connectedDebugAndroidTest
```

Для Windows використовуй gradlew.bat замість bash gradlew.

## Перевірка пункту 3

1. Натиснути +1 двічі: «Натискань: 2».
2. Відкрити статистику: «Усього натискань: 2».
3. Відкрити довідку.
4. Системним «Назад» повернутися до статистики, ще раз — до лічильника.
5. Повторити шлях і перевірити кнопку «Назад» у верхній панелі.
6. На статистиці повернути телефон: маршрут і число мають залишитися.
7. На довідці змінити вкладку й повернутися до лічильника: довідка має відновитися.
8. «На початок лічильника» має повернутися до першого підекрана.

Автоматизовані сценарії: NavigationFlowTest.
Перевірено GitHub Actions на коміті 087cb1940310858b2a617219b5541616770f36a5:
збірка APK, модульні тести, lint і тести на Android-емуляторі API 35 завершилися успішно.
Запуск: https://github.com/Daeddman/NavigationLab/actions/runs/45784763457
Це перевірка автоматичних сценаріїв; особистий запуск студентом і власні скріншоти
потрібно виконати окремо. Локальне середовище підготовки мало мережеве обмеження Java,
тому перевірку виконано на GitHub Actions.

## Протокол використання ШІ

Дата: 26.11.2026. Інструмент: ChatGPT / Codex.

Запит: знайти приклад лабораторної №2 у репозиторіях oboris і реалізувати
навігацію у проєкті попередньої роботи.

ШІ виконав:
- перегляд репозиторіїв Demo2026 та NewNav2025 і вибір відповідного прикладу;
- читання офіційної документації Android;
- перенесення підготовленої раніше MVVM-версії до проєкту, оскільки master
  містив стару реалізацію в одному MainActivity.kt;
- створення маршрутів, NavHost, двох нових підекранів і кнопок переходу;
- інтеграцію верхньої кнопки «Назад»;
- додавання залежностей, тестів, workflow та цього README.

Попередня MVVM-версія також була підготовлена за допомогою ШІ.
Автору роботи потрібно самостійно перевірити застосунок і розуміти
NavController, NavHost, composable, navigate, navigateUp, popBackStack,
callback, StateFlow та життєвий цикл ViewModel.

Поле для студента: фактичний пристрій, дата запуску, отримані результати
та власні зміни додаються після перевірки, без вигаданих підтверджень.
