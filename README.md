# 제안서 검색 시스템  <!-- omit in toc -->


## Table of Contents <!-- omit in toc -->
- [1. Project Overview](#1-project-overview)
    - [1.1 Project Background](#11-project-background)
        - [1.1.1 추진배경](#111-추진배경)
        - [1.1.2 프로젝트 목표 및 추진방향](#112-프로젝트-목표-및-추진방향)
        - [1.1.3 현황](#113-현황)
    - [1.2 Stakeholder List](#12-stakeholder-list)
    - [1.3 Business Goal List](#13-business-goal-list)

- [2. System Overview](#2-system-overview)
    - [2.1 System Context Diagram](#21-system-context-diagram)
    - [2.2 External Entity List](#22-external-entity-list)
    - [2.3 External Interface List](#23-external-interface-list)
    - [2.4 System Feature List](#24-system-feature-list)

- [3. Architectural Driver](#3-architectural-driver)
    - [3.1 Use Case Model](#31-use-case-model)
        - [3.1.1 Use Case Diagram](#311-use-case-diagram)
        - [3.1.2 Use Case List](#312-use-case-list)
    - [3.2.	Quality Attribute Scenario](#32-quality-attribute-scenario)
        - [3.2.1 QA Scenario List](#321-qa-scenario-list)
        - [3.2.2 제안서 검색 시간 최소화](#322-제안서-검색-시간-최소화-qualityattr-01)
        - [3.2.3 통합 데이터 관리](#323-통합-데이터-관리-qualityattr-02)
        - [3.2.4 다양한 플랫폼(모바일)](#324-다양한-플랫폼모바일-qualityattr-03)
        - [3.2.5 인증을 통한 권한](#325-인증을-통한-권한-qualityattr-04)
    - [3.3 Constraint](#33-constraint)
        - [3.3.1 Business Constraint List](#331-business-constraint-list)
        - [3.3.2 Technical Constraint List](#332-technical-constraint-list)

- [4 Architecture Strategies](#4-architecture-strategies)
    - [4.1 대상 품질 속성 시나리오 선정 ](#41-대상-품질-속성-시나리오-선정)
    - [4.2 아키텍처 드라이버 선정 ](#42-아키텍처-드라이버-선정)
    - [4.3 아키텍처 전략 검토](#43-아키텍처-전략-검토)

- [5. Architecture Design](#5-architecture-design)
    - [5.1 Runtime View](#5-architecture-design)
        - [5.1.1 System Overview](#511-system-overview)
        - [5.1.2 Was Server](#512-was-server)
        - [5.1.3 Business Module](#513-business-module)
    - [5.2 Runtime View](#52-runtime-view)
        - [5.2.1	모듈 도출](#521-모듈-도출)

## 1. Project Overview

### 1.1 Project Background

#### 1.1.1 추진배경

- 제안서는 고객이 공급자에게 자신들의 문제를 해결하기 위한 방법을 제안해 줄 것을 요청하는 제안 요청서(RFP: Request For Proposal)에 대해서 각 공급사별로 자신들의 해결 방안을 제시하면서 자신들을 공급자로 선정해 줄 것을 제안하는 문서입니다.
- 저희 회사는 공급자로 매번 고객이 요청한 RFP에 맞는 제안서를 작성하고 있습니다. 일부 특수한 영역을 제외한 대부분의 제안서는 비슷한 내용의 이전 제안서를 참고하여 작성하고 있습니다.
- 하지만 이전 제안서를 검색할 수 있는 기능이 없어서 필요한 경우 이전 제안서 문서를 하나씩 열어서 내용을 검색하고 있어 불필요한 시간 및 인력 낭비가 발생하고 있기에 새로운 제안 시스템에 대한 필요성이 제기되었습니다.


#### 1.1.2 프로젝트 목표 및 추진방향

- 본 프로젝트는 제안서, 제언서 작성시 이전 작성한 Word, Power Point, Excel 등의 문서에서 키워드 검색을 통해 이전에 작성한 문서에 쉽게 접근할 수 있는 것을 목표로 합니다.

<div style="display:inline-block; font-size:15px; padding-left:30px" >
    <p>■ 이전 제안서에서 키워드 검색을 통해 문서에 있는 특정 문자를 조회할 수 있습니다.</p>
    <p>■ 전체 문서를 다운받지 않고도 내용을 확인할 수 있는 미리보기 기능을 제공합니다.</p>
    <p>■ 인력 프로필 문서를 자동으로 산출할 수 있습니다.</p>
</div>


#### 1.1.3 현황

- 제안서 작성현황 (SI 특정 본부 기준)

| 구분| 현황 (2022년)|
| --| --|
| 1년 제안서 건수| 15건(제안서 10건, 제언서 5건)|
| 제안서 평균 mm| 투입인원 10명, 1개월 소요 10MM 정도 소요됨|

- 사내 제안서 통합검색 : 회사 내부에는 이미 제안서 통합검색 시스템이 있습니다. 하지만 직접 키워드를 입력해 놓는 방식이며, 각 페이지의 내용을 확인할 수 없고 직접 문서를 다운받는 방식입니다.

![사내_제안시스템1](./image/../사내_제안_시스템.png)

![사내_제안시스템2](./image/../사내_제안_시스템2.png)



### 1.2 Stakeholder List
#### 1.2.1 제안서 작성자
- 배경: 새로운 제안서를 작성해야되는 인력
- 관심사: 이전에 작성하였던 제안서를 빠르게 확인, 인력 프로필 자동 생성

#### 1.2.2 프로젝트 투입 예정자
- 배경: 제안서 수주되면 프로젝트 투입해야 되는 인력
- 관심사: 제안서마다 변경되는 인력 프로필 템플릿과 상관없이 자신의 프로필을 등록

#### 1.2.3 팀 관리 담당자
- 배경: 팀내 프로젝트 및 인력관리를 담당하는 인력
- 관심사: 팀내 프로젝트 및 인력관리를 액셀이 아닌 시스템으로 수행

#### 1.2.4 프로젝트 수행 인력
- 배경: 등록된 제안서의 프로젝트를 수행하는 인력
- 관심사: 프로젝트 수행중에 고객사와 협상을 위해 프로젝트 제안서를 확인 필요

#### 1.2.5 고객사
- 배경: 자신들의 문제를 해결하기 위해 RFP를 공고하고, 제안서를 받고 싶은 고객사
- 관심사: 한정된 시간내에 RFP 요건을 만족하는 수준높은 제안서를 받고 싶음

#### 1.2.6 사내 인사관리 시스템
- 배경: 사내 직원들의 프로젝트 수행인력을 관리하는 시스템

### 1.3 Business Goal List

| ID| Stakeholder| Business Goal| Importance|
| --| --| --| --|
| businessGoal-01| 제안서 작성자| 이전에 작성된 제안서를 쉽고 빠르게 접근| 상|
| businessGoal-02| 제안서 작성자| 고객사에서 제공한 템플릿 맞는 인력 프로필을 자동 생성| 상|
| businessGoal-03| 제안서 작성자| 이미 검색한 제안서의 중복적인 검색을 제한| 하|
| businessGoal-04| 제안서 작성자| 필요한 제안서 페이지에 대한 북마크 기능을 제공| 하|
| businessGoal-05| 제안서 작성자| 완성된 제안서를 시스템에 쉽게 등록| 상|
| businessGoal-06| 프로젝트 투입 예정자| 통합된 시스템으로 자신의 프로젝트 수행이력을 한번만 등록한다| 중|
| businessGoal-07| 프로젝트 투입 예정자| 자신의 프로젝트 수행이력을 쉽게 확인| 하|
| businessGoal-08| 팀 관리 담당자| 팀 내부의 인력 투입현황을 시스템으로 관리| 중|
| businessGoal-09| 팀 관리 담당자| 인력 투입현황을 보고서 형태로 출력| 하|
| businessGoal-10| 프로젝트 수행 인력| 프로젝트 수행시 필요한 부분의 제안서 내용을 확인| 상|
| businessGoal-11| 고객사| RFP 요건을 만족하는 수준높은 제안서 제공 받음| 중|

## 2. System Overview
### 2.1 System Context Diagram

![system_context_diagram](./image/../system_context_diagram.png)

### 2.2 External Entity List
> 신규 시스템으로 시스템 숙련도는 제외합니다

#### 2.2.1 제안서 작성자
- 유형: 사용자
- 역할: 제안서 검색 기능을 통해 이전 제안서를 검색하고, 인력프로필을 출력한다
- 핵심기대사항: 이전 제안서 조회시 편의성 및 정확성, 인력프로필 생성 자동화

#### 2.2.2 프로젝트 투입 예정자
- 유형: 사용자
- 역할: 자신의 프로젝트 투입 이력을 작성한다.
- 핵심기대사항: 반복적인 인력프로필 작성 중단

#### 2.2.3 팀 관리 담당자
- 유형: 사용자
- 역할: 팀 인력현황을 관리한다.
- 핵심기대사항: 시스템으로 팀 인력현황을 관리하여 효율성

#### 2.2.4 프로젝트 수행 인력
- 유형: 사용자
- 역할: 프로젝트 수행시 필요한 제안서 내용을 확인한다
- 핵심기대사항: 제안서 조회시 편의성 및 정확성

#### 2.2.5 고객사
- 유형: 사용자
- 배경: 제안서에 포함될 인력프로필 템플릿을 제공
- 핵심기대사항: 제안서의 품질 향상

#### 2.2.6 사내 인사관리 시스템
- 유형: 시스템
- 배경: 기존에 사내 인사정보를 제공한다
- 핵심기대사항: 없음

### 2.3 External Interface List

#### 2.3.1 제안서 검색 (키워드)
* 역할: 기존의 등록된 제언/제안서에서 특정 키워드로 검색기능을 제공한다
* 특성:
    * 특정 키워드를 검색 알고리즘을 통해 빠르게 접근
    * 파일 다운로드 없이 Web에서 미리보기 기능 제공

#### 2.3.2 인력 프로필 자동 생성
* 역할: 고객사에서 요청한 템플릿형태로 인력프로필을 자동으로 생성한다
* 특성:
    * 고객사마다 요청하는 인력프로필 형태가 다르다
    * 변경없는 인력프로필을 제안마다 작성하는 것은 불필요한 추가 업무


### 2.4 System Feature List

| ID| Title| Description| Importance| Related BG|
| --| --| ----| --|--|
| systemFeature-01| 제안서 검색 속도| 제안서의 Text 마다 검색이 되어야 함으로 검색       속도| 상|[businessGoal-01](#13-business-goal-list) [businessGoal-10](#13-business-goal-list)|
| systemFeature-02| UI 설계를 통한 사용자 편의성| 제안서 작성에 효율성을 높이기 위한 시스템으로 사용자 편의성| 중| [businessGoal-03 businessGoal-04 businessGoal-05 businessGoal-08](#13-business-goal-list)|
| systemFeature-03| 다른 플랫폼과 연계를 위한 확장성| 외부에 프로젝트 투입된 인력도 편하게 사용할 수 있게 외부 플랫폼(모바일)과 확장| 중| [businessGoal-06 businessGoal-07](#13-business-goal-list)|
| systemFeature-04| 역할 기반의 권한 제어| 역할에 따른 접근 가능 데이터에 대한 제한| 상| [businessGoal-01 businessGoal-08](#13-business-goal-list)|


## 3. Architectural Driver

### 3.1 Use Case Model

#### 3.1.1 Use Case Diagram

![Use_Case_Diagram](./image/../use_diagram.png)
### 3.1.2 Use Case List

#### 3.1.2.1 제안서를 검색 (userCase01)
* Description: 제안 작성자가 이전 프로젝트 제안/제언서를 키워드를 통해 검색한다
* Priority(I/D): 상/상
* Related System Feature ID: [systemFeature-01, systemFeature-04](#24-system-feature-list)

#### 3.1.2.2 중복검색 제한 (userCase02)
* Description: 제안 작성자가 이전에 검색하였던 제안서는 중복해서 검색하지 않도록 읽음 표시를 한다
* Priority(I/D): 하/하
* Related System Feature ID: [systemFeature-01, systemFeature-02](#24-system-feature-list)

#### 3.1.2.3 북마크 기능 (userCase03)
* Description: 제안 작성자가 이전에 검색하였던 제안서를 북마크하여 이후에도 쉽게 찾을 수 있다
* Priority(I/D): 하/하
* Related System Feature ID: [systemFeature-02](#24-system-feature-list)

#### 3.1.2.4 프로필 자동생성 (userCase04)
* Description: 제안 작성자는 고객사에게 받은 템플릿 형태로 프로필을 자동생성 한다
* Priority(I/D): 중/상
* Related System Feature ID: [systemFeature-02](#24-system-feature-list)

#### 3.1.2.5 수행이력 수정 (userCase05)
* Description: 프로젝트 투입예정자는 자신의 프로젝트 투입 이력을 수정한다
* Priority(I/D): 중/상
* Related System Feature ID: [systemFeature-02, systemFeature-03](#24-system-feature-list)

#### 3.1.2.6 수행이력 대량 등록 (userCase06)
* Description: 팀 관리 담당자는 인사시스템에서 다운받은 인사시스템 데이터를 한번에 등록할 수 있다
* Priority(I/D): 중/중
* Related System Feature ID: [systemFeature-02, systemFeature-04](#24-system-feature-list)

#### 3.1.2.7 투입현황을 보고서형태로 추력 (userCase07)
* Description: 팀 관리 담당자는 수행이력을 토대로 매주 보고서형태로 출력할 수 있다
* Priority(I/D): 하/하
* Related System Feature ID: [systemFeature-02](#24-system-feature-list)

#### 3.1.2.8 인력 프로필 템플릿 제공 (userCase08)
* Description: 고객사들의 자신들이 원한 형태의 인력 프로필 템플릿을 제공한다
* Priority(I/D): 중/하
* Related System Feature ID: [systemFeature-03](#24-system-feature-list)

#### 3.1.2.9 인사 데이터를 제공(userCase09)
* Description: 사내 인사관리 시스템에서 투입예정자들에 대한 인사 데이터를 제공한다
* Priority(I/D): 중/중
* Related System Feature ID: [systemFeature-03](#24-system-feature-list)


## 3.2.	Quality Attribute Scenario

### 3.2.1 QA Scenario List

| ID| Title| QA Type| I| D| Related SF|
| --| --| --|--|--|--|
| qualityAttr-01| 제안서 검색 시간 최소화| 사용성| 상| 상| [systemFeature-01](#24-system-feature-list)|
| qualityAttr-02| 통합 데이터 관리| 보안성, 변경가능성| 상| 상| [systemFeature-01](#24-system-feature-list)|
| qualityAttr-03| 다양한 플랫폼(모바일)| 편의성, 변경가능성| 중| 상| [systemFeature-02, systemFeature-03](#24-system-feature-list)|
| qualityAttr-04| 인증을 통한 권한 체크| 보안성| 상| 중| [systemFeature-04](#24-system-feature-list)|
| qualityAttr-05| 유지보수에 적은 비용이 소모| 변경가능성, 유지보수성| 상| 중| [systemFeature-02, systemFeature-03](#24-system-feature-list)|

### 3.2.2 제안서 검색 시간 최소화 (qualityAttr-01)
* QA Type: 사용성
* Description: 대량의 Text에서 특정 키워드만 조회시간을 최소화
* Environment:
    * 10년 이상의 제안서를 Text 별로 저장하게 되면, 수많은 데이터가 존재하게 된다.
    * 이를 키워드를 통한 검색에 시간이 소요된다.
* Response:	검색알고리즘으로 키워드가 존재하는 제안서 검색결과
* Response Measure: http 요청시 1.5 초안에 응답

### 3.2.3 통합 데이터 관리 (qualityAttr-02)
* QA Type: 보안성, 변경관리성
* Description: 통합 데이터를 통한 프로젝트 수행이력 관리
* Environment:
    * 고객사마다 다른 자신들의 인력 프로필 템플릿을 제공
    * 매번 프로젝트 수행인력이 직접 제출
* Response:	프로젝트 수행 인력이 매번 입력하던 프로필을 자동으로 사용
* Response Measure: 프로젝트 수행이력을 통합 관리


### 3.2.4 다양한 플랫폼(모바일) (qualityAttr-03)
* QA Type: 편의성, 변경가능성
* Description: PC 뿐 아니라 모바일에서 사용 가능
* Environment:
    * 외부 투입인력은 인터넷이 연결되지 않은 내부망에서 수행중
    * 도중에 프로필 수정을 위해서는 사내망 접속해야 함
    * 단 제안서 관련 내용은 노출 금지
* Response:	모바일 기기에서 사용가능
* Response Measure: 목표 기능의 테스트 성공률 100%


### 3.2.5 인증을 통한 권한 (qualityAttr-04)
* QA Type: 보안성
* Description: 개인정보는 없지만 사내 제안서 내용도 존재하기에 인증을 통한 보안체크
* Environment:
    * 각각 역할별로 권한을 부여
    * 인증을 통해 권한을 받을 수 있다
* Response:	인증을 통한 권한 확인
* Response Measure: 권한에 따른 테스트 성공률 100%


### 3.2.6 유지보수에 적은 비용이 소모 (qualityAttr-05)
* 변경가능성, 유지보수성
* Description: 적은 비용으로 유지보수 기능 구현
* Environment:
    * 유지보수 인력이 존재 하지 않음
    * 최대한 자동화를 통해 추가 작업 최소화
* Response:	테스트 코드 작성
* Response Measure: 사이드 이펙트 체크 자동화

## 3.3 Constraint

### 3.3.1 Business Constraint List

| ID| Title| Description|
| --| --| --| 
| businessConstraint-01| 일정| 2023-09월 ~ 2023년 10월 말까지 개발 완료|
| businessConstraint-02| 비용| N/A|
| businessConstraint-03| 인력| 개발자 2명|

### 3.3.2 Technical Constraint List

| ID| Title| Description|
| --| --| --| 
| technicalConstraint-01| Backend| 유지보수 측면에서 개발자 수급이 편한 Java 언어를 사용|
| technicalConstraint-02| Frontend| 화면이 단순하고, 짦은 개발기간을 고려해 별도의 CSR 방식으로 개발|
| technicalConstraint-03| DBMS| PostgreSql을 사용하고 제안서 데이터는 속도를 위한 noSql을 사용|


# 4 Architecture Strategies

## 4.1 대상 품질 속성 시나리오 선정
> 품질 속성 시나리오가 많지 않기 때문에 전체를 항목으로 선정

| ID| Title| QA Type| I| D| 선정|
| --| --| --|--|--|--|
| qualityAttr-01| 제안서 검색 시간 최소화| 사용성| 상| 상| Y|
| qualityAttr-02| 통합 데이터 관리| 변경가능성| 상| 상| Y|
| qualityAttr-03| 다양한 플랫폼(모바일)| 편의성, 변경가능성| 중| 상| Y|
| qualityAttr-04| 인증을 통한 권한 체크| 보안성| 상| 중| Y|
| qualityAttr-05| 유지보수에 적은 비용이 소모| 변경가능성, 유지보수성| 상| 중| Y|

## 4.2 아키텍처 드라이버 선정

| ID| Title| Type| Related QA|
| --| --| --|--|
| architectureDriver-01| 대용량 데이터 처리 방식| 사용성, 변경가능성| [qualityAttr-01](#322-제안서-검색-시간-최소화-qualityattr-01), [qualityAttr-02](#323-통합-데이터-관리-qualityattr-02)| 
| architectureDriver-02| 추가 기능에 대한 영향도 최소화| 편의성, 변경가능성| [qualityAttr-03](#324-다양한-플랫폼모바일-qualityattr-03), [qualityAttr-05](#3126-수행이력-대량-등록-usercase06)| 
| architectureDriver-03| 테스트 방식 (유지보수 비용 최소화)| 유지보수성| [qualityAttr-05](#3126-수행이력-대량-등록-usercase06)| 


## 4.3 아키텍처 전략 검토

### 4.3.1 대용량 데이터 처리 방식

* 결정사항: RDBMS로 일반적인 데이터 처리를 수행하고, 검색과 분석에 필요한 필드만 커스텀 매핑으로 ElasticSearch 에 동기화 하는 방식으로 데이터베이스를 보조하는 search engine으로 사용

* 대안비교
    * 일반적인 RDBMS 사용
        * 설명: 일반적인 RDBMS (Oracle, PostgreSql, MySql) 등을 사용
        * 장점: 숙련도가 있어서 개발속도가 빠름, 직관적인 데이터 표현, 정형데이터 읽기 쓰기 작업에 적합
        * 단점: 비정형데이터 읽기 업무에 대한 성능을 보장못함 (제안서 검색은 비정형 텍스트 데이터로 적합하지 않음)

    * Redis 사용
        * 설명: Key, Value 구조의 비정형 데이터를 저장하고 관리하기 위한 오픈 소스 기반의 비관계형 데이터 베이스 관리 시스템으로
          주로 캐쉬서버에 사용되지만 NO-SQL DB로 사용도 가능
        * 장점: 메모리에서 데이터를 처리하기 때문에 작업속도가 빠르다
        * 단점: 싱글 쓰레드, 점차 데이터를 쌓아가는 환경에 적합하지 않음

    * ElasticSearch 사용
        * 설명: Elasticsearch는 Apache Lucene기반의 Java 오픈소스 분산 검색 엔진이다. 기본적으로 데이터를 인덱싱하여 저장하고, 검색, 집계등의 기능을 수행한다
        * 장점: 기존 RDBMS에서 다루기 어려운 full text search 기능이 제공, 오픈소스 정보검색(루씬) 라이브러리 사용가능
        * 단점: 트랜잭션 개념이 없다. 데이터 입력이 많으면 빠른 검색 기능을 제대로 활용 불가

### 4.3.2 추가 기능에 대한 영향도 최소화

* Backend : 어플리케이션 프레임워크는 Spring Framework(Boot) 선택 (마땅한 대안이 없음), 스프링으로 사용할 수 있는 자바/코틀린/그루비 중에 가장 허들이 낮고 개발자 수급이 편한 자바로 선택.
*  UI Redering 방식은 SSR, CSR 중에 관리자 페이지 정도의 UI 화면 수와 난이도를 고려하고, 실제 사용자 수도 많지 않기 때문에 서버 부하에 민감하지 않기 떄문에 SSR 방식을 선택함. SSR 방식중에서도 Spring Framework 와 호환성이 좋은 타임리프 프레임워크를 사용

* 대안비교 (Backend)
    * Java
        * 장점: 개발자 수급 가능, 많은 래퍼런스 존재
        * 단점: 코드 작성 난이도가 있음 (코틀린 등의 비교적 신생언어에 비해)
    * Kotlin
        * 장점: Java와 호환 가능하면서 더 간결한 언어, 비동기 코드 작성 편함
        * 단점: 개발자 수급이 어려움, 래퍼런스 부족

* 대안비교 (Frontend)
    * CSR (reactJs, VueJs)
        * 설명: Client side rendering 으로 브라우저가 직접 화면 렌더링을 수행하는 방식
        * 장점: 서버트래픽 감소, 사용자에게 더 빠른 인터렉션 제공
        * 단점: frontend 개발자 필요, 첫 페이지 로딩 속도 오래걸림
    * SSR (JSP, 타임리프)
        * 설명: Server side rendering 으로 server에서 화면 렌더링 후에 클라이언트에 제공하는 방식
        * 장점: 초기 로딩속도가 빠름, frontend 개발자 필요없음
        * 단점: 서버트래픽 증가, CCR에서 제공하는 인터렉션 제공 불가
        *
### 4.3.3 테스트 방식 (유지보수 비용 최소화)
* 결정사항: 팀내 솔루션이다 보니 유지보수 인력이나 테스터는 없고 요구사항이나 애러는 있을 것이기 때문에 테스트 코드를 작성하여 애러 발생과 수동으로 테스트 해야되는 범위를 최소화 한다.

* 대안비교
    * 테스트코드 작성 (TDD)
        * 장점: 개발자가 스스로 모듈에 대한 검증을 하며, 테스트 코드가 돌아가는 어플리케이션 코드는 구조가 간단해진다. 자동으로 테스트
        * 단점: 어플리케이션 코드 작성시간 만큼 테스트 코드 작성에 시간이 소요된다
    * 수동테스트:
        * 장점: 개발시간이 단축된다 (테스트코드를 작성하지 않아도 되니깐)
        * 단점: 별도의 테스터나 테스트 시간이 필요하며, 반복적인 작업이 필요


# 5. Architecture Design

## 5.1 Runtime View
#### 5.1.1 System Overview
> 시스템 전체의 Overview

![Overview](./image/../system_overview.png)
* 사용자는 Web browser를 통해 was server와 통신하고 그 결과를 HTML로 받는다
#### 5.1.2 Was Server
> WAS 내의 컴포넌트 간의 전체적인 동작 흐름도를 설명

![Overview](./image/../was.png)

* (1) WAS(Tomcat)으로 RESTFul 방식의 API를 호출
* (2),(3) 인증/인가에 대한 프로세스는 Spring scecurity 필터를 통해 호출된다
* (4) Spring MVC를 통해 인가된 호출이 들어온다
* (5) Servlet Dispacher를 통해 요청은 Business Moduled을 호출한다
* (6) JPA를 통해 DB 에 접속한다
* (7) 제안서 관련이 아닌경우 일반 DBMS 접속
* (8) 제안서 관련 내용은 ElasticSearch 에 접속하여 데이터 조회

#### 5.1.3 Business Module

![Business_Module](./image/../business.png)

| Module| Description|
| --| --|
| Project| 제안서 등록하기 위한 프로젝트 구분|
| Proposal| 기존에 작성하였던 제안서를 등록하고 조회한다|
| Sheet| 등록된 제안서를 각 페이지별로 별도 저장하고 조회시 구분되어 확인할 수 있다|
| Team| 사용자를 분류한다|
| User| 사용자/역할 정보를 제공한다|
| Bookmark| 사용자가 추후 더 보고 싶은 경우 관리 할 수 있다|
| History| 사용자가 확인한 내용은 재검색되지 않게 한다|
| Profile| 사용자의 프로젝트 수행이력을 확인한다|
| Teamplate| 인력 프로필을 생성하는 작업을 수행한다|


## 5.2 Runtime View

### 5.2.1	모듈 도출
> 도출된 모듈은 아래와 같습니다.

![domain](./image/../domain.png)

* 작은 시스템이라 따로 배포 단위를 나누지 않음 다만 도메인 구분은 나름대로 작업

![domain](./image/../domain2.png)

 
