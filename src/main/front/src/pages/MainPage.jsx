import React, { useEffect, useState } from "react";
import Header from "../components/Header/Header";
import axios from "axios";

const MainPage = () => {
    const [hello, setHello] = useState('');

    // 연습용 데이터
    const [user, setUser] = useState(null);
    const [error, setError] = useState(null);
    const id = 1; // 예시로 1번 유저 가져옴

    useEffect(() => {
        // API 요청: 'http://localhost:8080/api/test'로부터 데이터 받아오기
        axios.get('http://localhost:8080/api/test')
            .then((res) => {
                setHello(res.data);
            });

        // 사용자 정보 API 요청 (id를 경로 변수로 전달)
        axios.get(`http://localhost:8080/members/1`)
            .then((res) => {
                console.log(res);
                setUser(res.data);
            })
            .catch((err) => {
                console.log(err);
                setError("회원 정보 가져오기 실패!" + err);
                console.error(err);
            }); // catch 구문 닫힘
    }, []); // 의존성 배열 빈 배열로 설정, 컴포넌트 마운트 시 한 번만 실행

    return (
        <div className="App">
            <Header />
            <div>
                <p>백엔드 데이터: {hello}</p>
                {error && <p>{error}</p>}
                {user ? (
                    <div>
                        <p>사용자 이름: {user.name}</p>
                        <p>이메일: {user.email}</p>
                        <p>전화번호: {user.phoneNumber}</p>
                    </div>
                ) : (
                    <p>사용자 정보를 불러오는 중...</p>
                )}
            </div>
        </div>
    );
};

export default MainPage;
