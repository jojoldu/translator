# translator (IntelliJ 번역 플러그인)

주석 해석, 변수명 찾기 등 개발 중 번역기를 사용하는 일이 빈번합니다.  
구글 번역창, 네이버 사전을 항상 열어놓는것도 귀찮아 번역 플러그인 제작을 시작하였습니다.

## 사용법

### 기본 번역

단축키 : ```option+1```

![예제1](./images/예제1.gif)

### 번역 및 변환

단축키 : ```option+2```

![에제2](./images/예제2.gif)

### API KEY 발급

구글 번역 API는 **유료**만 있습니다.  
그래서 **MS Azure API**를 사용하였습니다.  
(Azure의 경우 **월 200만**건 까지 무료입니다.)  
아래 링크를 참고하여 MS Azure API Key를 발급 받아주세요  
(등록하지 않을 경우 월 200 쿼리만 실행 가능합니다.)

* [MS Guide](http://docs.microsofttranslator.com/text-translate.html)

### API KEY 등록

발급 받은 Key를 아래 위치에 등록해주세요.

![설정](./images/설정.png)

등록 후, 바로 기능을 사용하시면 됩니다.

## 참고

* [차영호님의 플러그인 제작 발표 자료](https://news.realm.io/kr/news/android-studio-plugin-development/)
* [중국인이 만든 번역 플러그인](https://github.com/YiiGuxing/TranslationPlugin)
* [IntelliJ 텍스트 관련 플러그인 메뉴얼](http://www.jetbrains.org/intellij/sdk/docs/tutorials/editor_basics/working_with_text.html)
* [IntelliJ 컴포넌트 위치 관련 플러그인 메뉴얼](http://www.jetbrains.org/intellij/sdk/docs/tutorials/editor_basics/coordinates_system.html)
* [IntelliJ 플러그인 적용 가능한 버전 확인을 위한 빌드넘버 리스트](https://www.jetbrains.com/intellij-repository/releases)
* [Gradle 프로젝트로 IntelliJ 플러그인 제작 메뉴얼](http://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system/prerequisites.html)
* [gradle-intellij-plugin](https://github.com/JetBrains/gradle-intellij-plugin)
* [Plugin FAQ](http://www.jetbrains.org/intellij/sdk/docs/faq.html)
* [Plugin 설정 기능 가이드](http://corochann.com/intellij-plugin-development-introduction-applicationconfigurable-projectconfigurable-873.html)