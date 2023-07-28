# android-protect-app-simpleversion
## 기능
### 1. 인증화면 구성
 - exported=true 취약점으로 해당 인증기능 우회 실습을 위한 구현.
 - 특정 키 값을 입력하여 인증이 완료되면 특정 화면으로 이동하도록 구성 하였음.
 - (본래 로그인 및 회원가입 기능으로 php + mysql 로 구성 하였으나 실습을 하여 해당 방법으로 변경)

### 2. DeepLink 버튼 구현
 - DeepLink 취약점 연구 및 앱 변조를 통한 취약점 실습을 위한 구현.

### 2. Rooting Check 구현
 - Frida를 통한 Root checking 우회 실습환경을 위한 구현.
 - 루팅 시 생성되는 파일을 체크하여 system.exit 함수를 호출.

### 3. native c++로 Anti-Frida 구현
 - Anti-Frida 우회 실습환경을 위한 구현.
 - /data/local/tmp 의 frida 를 포함하는 File & Directory 가 존재할 시 system.exit 함수를 호출.
