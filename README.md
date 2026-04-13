# 🚀 OpenStack-Based Hybrid AIaaS Dashboard
> **오픈스택 프라이빗 클라우드와 퍼블릭 AI 서비스를 통합한 개인 인프라 관리 대시보드**

![Infrastructure](https://img.shields.io/badge/Infrastructure-OpenStack-red) ![Platform](https://img.shields.io/badge/AIaaS-AWS_Lex_&_Rekognition-orange) ![DevOps](https://img.shields.io/badge/Automation-Ansible_&_OpenClaw-white)

## 📌 1. Project Overview (프로젝트 개요)
본 프로젝트는 **OpenStack**을 활용하여 직접 구축한 프라이빗 클라우드 환경 위에, 외부 **AI API(AIaaS)**를 연동한 대시보드형 홈페이지를 구축하는 것을 목표로 합니다. 
단순한 웹 서비스를 넘어, 인프라의 자동화 배포와 하이브리드 클라우드 전략을 실무적으로 구현하였습니다.

* **주요 기능:**
    * **Space One (Infra Monitor):** OpenStack 인스턴스 자원 및 네트워크 상태 실시간 확인
    * **Space Two (AI Playground):** AWS Lex 및 Rekognition 기반의 지능형 서비스 테스트 베드
    * **Space Three (Cloud Management):** Ansible 및 OpenClaw를 이용한 인프라 자동화 관리

---

## 🏗️ 2. System Architecture (시스템 아키텍처)
이 프로젝트는 **Neutron 외부망 연동**을 통해 로컬 자원과 퍼블릭 클라우드를 유기적으로 연결하는 '하이브리드 구조'를 가집니다.

```mermaid
graph TD
    subgraph "External World"
        User((User/Admin))
        AI_Cloud[AWS AI Services: Lex, Rekognition]
    end

    subgraph "OpenStack Private Cloud"
        Router[Neutron Router / Floating IP]
        Web_Server[Web Dashboard Server: Spring Boot]
        DB_Server[Database Server: MySQL]
        
        Router <--> Web_Server
        Web_Server <--> DB_Server
    end

    User <--> Router
    Web_Server <--> AI_Cloud