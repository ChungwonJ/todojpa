# TodoJpa
이 프로젝트는 Todo 항목을 관리하기 위한 RESTful API입니다. 사용자는 회원가입, Todo 항목을 생성, 조회, 수정, 삭제 Todo 목록에 댓글 생성, 조회, 수정, 삭제할 수 있습니다.
# API 명세서
| 기능       | Method | URL              | Request Body                                             | Response                                                 |
|------------|--------|------------------|---------------------------------------------------------|---------------------------------------------------------|
| 회원가입       | POST   | /users/signup | { "username": "이름","password": "비밀번호","email" : "이메일" } | { "id": 1, "username": "이름", "email": "이메일", "createdAt": "2025-02-12T15:43:14.763805","modifiedAt" : "2025-02-12T15:43:14.763805" } |
| 로그인  | POST    |/users/signin  | "email" : "이메일","password": "비밀번호"| [ { "username": "이름","email": "이메일","createdAt": "2025-02-12T15:43:14.763805","modifiedAt" : "2025-02-12T15:43:14.763805"  ] |
| 로그인세션  | GET    | /users/session-info     | -                                                       | 현재 사용자: 이름 |
| 회원조회       | GET    | /users/{id}     | - | { "username": "이름", "email" : "이메일","createdAt": "2025-02-12T15:43:14.763805","modifiedAt" : "2025-02-12T15:43:14.763805" } |
| 비밀번호 변경       | PATCH | /users/{id}     | { "oldPassword": "기존비밀번호", "newPassword" : "새로운 비밀번호" }                           | -                                                       |
| 삭제       | DELETE | /users/{id}     | { "password": "비밀번호" }                           | -                                                       |
| 글작성  | POST    |/todos  | "title" : "할 일 제목","contents": "할 일 내용"| [ {"id": 1, "username": "이름","title": "할 일 제목","contents": "할 일 내용","createdAt": "2025-02-12T15:43:14.763805","modifiedAt" : "2025-02-12T15:43:14.763805"}  ] |
| 글조회  | GET    | /todos/{id}   | - | {"id": 1, "username": "이름","title": "할 일 제목","contents": "할 일 내용","createdAt": "2025-02-12T15:43:14.763805","modifiedAt" : "2025-02-12T15:43:14.763805"} |
| 글수정       | PATCH    | /todos/{id}     | { "password" : "비밀번호","title": "수정제목","contents" : "수정내용" } | { "title": "수정제목","contents" : "수정내용","createdAt": "2025-02-12T15:43:14.763805","modifiedAt" : "2025-02-12T15:43:14.763805" } |
| 글삭제       | DELETE | /todos/{id}     | { "password": "비밀번호" }                           | -                                                       |
| 댓글추가  | POST    |/todos/{todoId}/comments | "content" : "댓글"| [ {"id": 1, "username": "이름","contents": "댓글","createdAt": "2025-02-12T15:43:14.763805","modifiedAt" : "2025-02-12T15:43:14.763805"}  ] |
| 댓글조회  | GET    |/todos/{todoId}/comments  | - | {"id": 1, "username": "이름","contents": "댓글","createdAt": "2025-02-12T15:43:14.763805","modifiedAt" : "2025-02-12T15:43:14.763805"}  |
| 댓글수정       | PATCH    | /todos/{todoId}/comments/{commentId} | { "password" : "비밀번호","content": "댓글수정"} | {"id": 1, "username": "이름","contents": "댓글수정","createdAt": "2025-02-12T15:43:14.763805","modifiedAt" : "2025-02-12T15:43:14.763805"} |
| 댓글삭제       | DELETE | /todos/{todoId}/comments/{commentId}    | { "password": "비밀번호" }                           | -                                                       |
# ERD
![Image](https://github.com/user-attachments/assets/bef11fc3-59e2-4694-a5f6-a6b7b2e3090a)
