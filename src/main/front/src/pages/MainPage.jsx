import { useEffect, useState } from "react";
import Header from "../components/Common/Header";
import axios from "axios";
import mainVisual from "../assets/Images/05_Kv_PC_Light_B.8067bd3b.webp";
import KakaoPayButton from "../components/Kakao/KakaoPayButton";

const MainPage = () => {
    const [hello, setHello] = useState('');

    // 연습용 데이터
    const [user, setUser] = useState(null);
    const [error, setError] = useState(null);
    const id = 1; // 예시로 1번 유저 가져옴

    // 숙소 검색 데이터
    const [accommodations, setAccommodations] = useState([]);
    const [searchError, setSearchError] = useState(null);

    // 검색 폼 상태
    const [searchName, setSearchName] = useState('');
    const [searchType, setSearchType] = useState('ALL');
    const [searchAddress, setSearchAddress] = useState('');

    // enum 값을 가져오기 위한 상태
    const [types, setTypes] = useState([]);

    useEffect(() => {
        // API 요청: 'http://localhost:8080/api/test'로부터 데이터 받아오기
        axios.get('http://localhost:8080/api/test')
            .then((res) => {
                setHello(res.data);
            });

        // 사용자 정보 API 요청 (id를 경로 변수로 전달)
        axios.get(`http://localhost:8080/members/id/${id}`)
            .then((res) => {
                console.log(res);
                setUser(res.data);
            })
            .catch((err) => {
                console.log(err);
                setError("회원 정보 가져오기 실패!" + err);
                console.error(err);
            }); // catch 구문 닫힘

        // enum 값 가져오기
        axios.get('http://localhost:8080/accommodation/types')
            .then((res) => {
                setTypes(res.data);
            })
            .catch((err) => {
                console.error("숙소 타입 가져오기 실패!", err);
            });
    }, []); // 의존성 배열 빈 배열로 설정, 컴포넌트 마운트 시 한 번만 실행

    const handleSearch = () => {
        // 숙소 검색 API 요청
        axios.get('http://localhost:8080/accommodation/search', {
            params: {
                name: searchName || '', // 검색할 숙소명
                type: searchType === 'ALL' ? '' : searchType, // 전체 선택 시 빈 문자열로 처리
                address: searchAddress || '', // 검색할 숙소 주소
                page: 0, // 페이지 번호
                size: 10 // 페이지 사이즈
            }
        })
            .then((res) => {
                setAccommodations(res.data.content); // 검색 결과를 상태에 저장
            })
            .catch((err) => {
                console.log("숙소 검색 실패:", err);
                setSearchError("숙소 검색 실패!" + err);
            });
    };

    return (
        <div className="App">
            <Header />
            <main>
                <section aria-label="상단 메인 비주얼" className="home-search-section">
                    <div className="main-visual contents flex-column">
                        <h1 className="main-visual__title">파리부터 부산 게하까지,<br /> 여행할때 여기어때</h1>
                        <div className="main-search-wrapper">
                            <div className="main-search__mobile">
                                <div>
                                    <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg"><path fillRule="evenodd" clipRule="evenodd" d="M8.85 16.1a6.707 6.707 0 004.394-1.642l-.027.034 3.4 3.508L18 16.574l-3.47-3.58A7.163 7.163 0 0015.7 9.05C15.7 5.156 12.633 2 8.85 2 5.067 2 2 5.156 2 9.05c0 3.894 3.067 7.05 6.85 7.05zm0-1.99c2.695 0 4.88-2.263 4.88-5.055S11.545 4 8.85 4 3.97 6.263 3.97 9.055s2.185 5.055 4.88 5.055z" fill="current"></path></svg>
                                </div>
                                <span>여행지나 숙소를 검색해보세요.</span>
                            </div>
                        </div>
                    </div>
                    <img src={mainVisual} alt="메인 비주얼 이미지" className="main-visual__img" />
                </section>
            </main>
            <div className="contents flex-column">
                <p>백엔드 데이터: {hello}</p>
                {error && <p>{error}</p>}
                {user ? (
                    <div>
                        <p>사용자 이름: {user.name}</p>
                        <p>이메일: {user.loginId}</p>
                        <p>전화번호: {user.phone}</p>
                    </div>
                ) : (
                    <p>사용자 정보를 불러오는 중...</p>
                )}

                <div>
                    <h2>숙소 검색</h2>
                    <input
                        type="text"
                        placeholder="숙소명"
                        value={searchName}
                        onChange={(e) => setSearchName(e.target.value)}
                    />
                    <select
                        value={searchType}
                        onChange={(e) => setSearchType(e.target.value)}
                    >
                        <option value="ALL">전체</option>
                        {types.map((type) => (
                            <option key={type} value={type}>
                                {type}
                            </option>
                        ))}
                    </select>
                    <input
                        type="text"
                        placeholder="숙소 주소"
                        value={searchAddress}
                        onChange={(e) => setSearchAddress(e.target.value)}
                    />
                    <button onClick={handleSearch}>검색</button>
                </div>
                <KakaoPayButton/>
                <div>
                    <h2>숙소 검색 결과</h2>
                    {searchError && <p>{searchError}</p>}
                    {accommodations.length > 0 ? (
                        <ul>
                            {accommodations.map((accommodation) => (
                                <li key={accommodation.id}>
                                    <p>이름: {accommodation.name}</p>
                                    <p>타입: {accommodation.type}</p>
                                    <p>주소: {accommodation.address}</p>
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <p>검색 결과가 없습니다.</p>
                    )}
                </div>
            </div>
        </div>
    );
};

export default MainPage;
