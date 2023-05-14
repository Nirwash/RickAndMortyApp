<h1 align="center">RickAndMortyApp</a> 
<img src="https://github.com/Nirwash/RickAndMortyApp/blob/master/icegif-519_resize64.gif" height="64"/></h1>

### Приложение реализовано на фрагментах (подход Single Activity), написано по Clean architecture и MVVM Pattern, основной язык программирования - Kotlin, а также Java (детальная информация о персонажах и локациях)
### API - https://rickandmortyapi.com/

## Технологии
* Kotlin
* Java
* Glide
* Coroutines
* Flow/LiveData
* RxJava
* Retrofit
* Pagging3
* Room
* Dagger2
* MVVM, Clean architecture


## функционал

* запуск

При запуске приложения отображается заставка с фирменным логотипом.
<img src="https://github.com/Nirwash/RickAndMortyApp/blob/master/screen_shot_scplash_screen.jpg" width=40% height=40%>

* Основной экран 

Основной экран состоит из 3 вкладок: "Characters", "Locations" и "Episodes", в каждой вкладке отображается соответствующий список. 
Список можно обновить свайпом вниз. При нажатии на элемент списка открывается детальная информация о выбранном элементе. Также есть кнопка фильтрации, которая открывает окно, где можно отсортировать элементы с заданными фильтрами.

<img src="https://github.com/Nirwash/RickAndMortyApp/blob/master/screen_shot_characters.jpg" width=30% height=30%><img src="https://github.com/Nirwash/RickAndMortyApp/blob/master/screen_shot_locations.jpg" width=30% height=30%><img src="https://github.com/Nirwash/RickAndMortyApp/blob/master/screen_shot_episodes.jpg" width=30% height=30%>

* Фильтрация списка:

Здесь можно применить различные фильтры, такие как поиск по имени, типу и т.д. Также можно очистить примененные ранее фильтры.

<img src="https://github.com/Nirwash/RickAndMortyApp/blob/master/screen_shot_character_filters.jpg" width=30% height=30%><img src="https://github.com/Nirwash/RickAndMortyApp/blob/master/screen_shot_location_filters.jpg" width=30% height=30%><img src="https://github.com/Nirwash/RickAndMortyApp/blob/master/screen_shot_episode_filters.jpg" width=30% height=30%>

* Экраны с детальной информацией элемента списка:

Здесь отображается более подробная информация об элементе(персонаже, локации или эпизоде). Также в случае персонажа, имеется список эпизодов и локаций в которых он появляется. Кликом по эпизоду или локации, можно пройти на детальный экран. При нажатии на стрелку назад, происходит возврат на предыдущий экран. В случае локации или эпизода, отображается список с персонажами, по клику на которых можно перейти на экран детальной информации об этом персонаже. Также поддерживается навигация назад.

<img src="https://github.com/Nirwash/RickAndMortyApp/blob/master/screen_shot_character_details.jpg" width=30% height=30%><img src="https://github.com/Nirwash/RickAndMortyApp/blob/master/screen_shot_location_details.jpg" width=30% height=30%><img src="https://github.com/Nirwash/RickAndMortyApp/blob/master/screen_shot_episode_details.jpg" width=30% height=30%>

