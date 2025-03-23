<<<<<<< HEAD
import { useEffect, useState } from "react";
import Header from "../components/Common/Header";
import axios from "axios";
import mainVisual from "../assets/Images/05_Kv_PC_Light_B.8067bd3b.webp";
=======
import { useEffect, useState } from 'react';
import Header from '../components/Common/Header';
import axios from 'axios';

// swiper import
import { Swiper, SwiperSlide } from 'swiper/react';
import { Autoplay, Pagination } from 'swiper';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import 'swiper/css/scrollbar';

// 이미지 import
import mainVisual from '../assets/Images/05_Kv_PC_Light_B.8067bd3b.webp';
import eventImg01 from '../assets/Images/img-event01.jpg';
import domestic_jeju_01 from '../assets/Images/img-region_jeju_01.jpeg';
>>>>>>> master

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

    // slide 임시 배열
    const eventSlide = [
        { id: '1', event: 'eventImg01' },
        { id: '2', event: 'eventImg01' },
        { id: '3', event: 'eventImg01' },
        { id: '4', event: 'eventImg01' },
        { id: '5', event: 'eventImg01' },
        { id: '6', event: 'eventImg01' },
        { id: '7', event: 'eventImg01' },
        { id: '8', event: 'eventImg01' },
        { id: '9', event: 'eventImg01' },
        { id: '10', event: 'eventImg01' },
    ];
    const domesticTravel = [
        { id: '1', name: '제주도' },
        { id: '2', name: '서울' },
        { id: '3', name: '부산' },
        { id: '4', name: '강릉' },
        { id: '5', name: '인천' },
        { id: '6', name: '경주' },
        { id: '7', name: '해운대' },
        { id: '8', name: '가평' },
        { id: '9', name: '여수' },
        { id: '10', name: '속초' },
    ];
    const overseasTravel = [
        { id: '1', name: '오사카' },
        { id: '2', name: '도쿄 / 동경' },
        { id: '3', name: '나트랑 / 나짱' },
        { id: '4', name: '후쿠오카' },
        { id: '5', name: '다낭' },
        { id: '6', name: '괌' },
        { id: '7', name: '싱가포르' },
        { id: '8', name: '방콕' },
        { id: '9', name: '교토' },
        { id: '10', name: '코타키나발루' },
    ];

    useEffect(() => {
        // API 요청: 'http://localhost:8080/api/test'로부터 데이터 받아오기
        axios.get('http://localhost:8080/api/test').then((res) => {
            setHello(res.data);
        });

        // 사용자 정보 API 요청 (id를 경로 변수로 전달)
        axios
            .get(`http://localhost:8080/members/id/${id}`)
            .then((res) => {
                console.log(res);
                setUser(res.data);
            })
            .catch((err) => {
                console.log(err);
                setError('회원 정보 가져오기 실패!' + err);
                console.error(err);
            }); // catch 구문 닫힘

        // enum 값 가져오기
        axios
            .get('http://localhost:8080/accommodation/types')
            .then((res) => {
                setTypes(res.data);
            })
            .catch((err) => {
                console.error('숙소 타입 가져오기 실패!', err);
            });
    }, []); // 의존성 배열 빈 배열로 설정, 컴포넌트 마운트 시 한 번만 실행

    const handleSearch = () => {
        // 숙소 검색 API 요청
        axios
            .get('http://localhost:8080/accommodation/search', {
                params: {
                    name: searchName || '', // 검색할 숙소명
                    type: searchType === 'ALL' ? '' : searchType, // 전체 선택 시 빈 문자열로 처리
                    address: searchAddress || '', // 검색할 숙소 주소
                    page: 0, // 페이지 번호
                    size: 10, // 페이지 사이즈
                },
            })
            .then((res) => {
                setAccommodations(res.data.content); // 검색 결과를 상태에 저장
            })
            .catch((err) => {
                console.log('숙소 검색 실패:', err);
                setSearchError('숙소 검색 실패!' + err);
            });
    };

    return (
        <div className="App">
            <Header />
            <main>
                <section aria-label="상단 메인 비주얼" className="home-search-section">
                    <div className="main-visual contents flex-column">
<<<<<<< HEAD
                        <h1 className="main-visual__title">파리부터 부산 게하까지,<br /> 여행할때 여기어때</h1>
                        <div className="main-search-wrapper">
                            <div className="main-search__mobile">
                                <div>
                                    <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg"><path fillRule="evenodd" clipRule="evenodd" d="M8.85 16.1a6.707 6.707 0 004.394-1.642l-.027.034 3.4 3.508L18 16.574l-3.47-3.58A7.163 7.163 0 0015.7 9.05C15.7 5.156 12.633 2 8.85 2 5.067 2 2 5.156 2 9.05c0 3.894 3.067 7.05 6.85 7.05zm0-1.99c2.695 0 4.88-2.263 4.88-5.055S11.545 4 8.85 4 3.97 6.263 3.97 9.055s2.185 5.055 4.88 5.055z" fill="current"></path></svg>
=======
                        <h1 className="main-visual__title">
                            파리부터 부산 게하까지,
                            <br /> 여행할때 여기어때
                        </h1>
                        <div className="main-search-wrapper">
                            <div className="main-search__mobile">
                                <div>
                                    <svg
                                        width="20"
                                        height="20"
                                        viewBox="0 0 20 20"
                                        fill="none"
                                        xmlns="http://www.w3.org/2000/svg">
                                        <path
                                            fillRule="evenodd"
                                            clipRule="evenodd"
                                            d="M8.85 16.1a6.707 6.707 0 004.394-1.642l-.027.034 3.4 3.508L18 16.574l-3.47-3.58A7.163 7.163 0 0015.7 9.05C15.7 5.156 12.633 2 8.85 2 5.067 2 2 5.156 2 9.05c0 3.894 3.067 7.05 6.85 7.05zm0-1.99c2.695 0 4.88-2.263 4.88-5.055S11.545 4 8.85 4 3.97 6.263 3.97 9.055s2.185 5.055 4.88 5.055z"
                                            fill="current"></path>
                                    </svg>
>>>>>>> master
                                </div>
                                <span>여행지나 숙소를 검색해보세요.</span>
                            </div>
                        </div>
                    </div>
                    <img src={mainVisual} alt="메인 비주얼 이미지" className="main-visual__img" />
                </section>
<<<<<<< HEAD
=======
                <section
                    aria-label="메인 이벤트"
                    className="home-slide-section contents flex-column">
                    <div className="home-slide__title">
                        <div className="home-slide__flex">
                            <h2 className="slide__title">이벤트</h2>
                            <a
                                role="link"
                                className="link-more"
                                aria-disabled="false"
                                target="_self"
                                href="/event#HOT">
                                <span>더보기</span>
                            </a>
                        </div>
                    </div>
                    <div className="home-slide__cont">
                        {/* 추후 컴포넌트로 빼야함 */}
                        <Swiper
                            spaceBetween={12}
                            pagination={{ dynamicBullets: true }}
                            loop={true}
                            modules={[Pagination, Autoplay]}
                            autoplay={{
                                delay: 5000,
                                disableOnInteraction: false,
                            }}
                            slidesPerView={'auto'}
                            centeredSlides={true}
                            centerInsufficientSlides={true}
                            className="home-slide__event">
                            {/* slide map */}
                            {eventSlide.map((item) => (
                                <SwiperSlide key={item.id}>
                                    <a href="" className="slide__link">
                                        <img
                                            src={`/src/assets/Images/img-event01.jpg`}
                                            className="slide__img"
                                        />
                                    </a>
                                </SwiperSlide>
                            ))}
                        </Swiper>
                    </div>
                </section>
                <section
                    aria-label="국내 인기 여행지"
                    className="home-slide-section contents flex-column">
                    <div className="home-slide__title">
                        <div className="home-slide__flex">
                            <h2 className="slide__title">국내 인기 여행지</h2>
                        </div>
                    </div>
                    <div className="home-slide__cont">
                        {/* 추후 컴포넌트로 빼야함 */}
                        <Swiper
                            spaceBetween={12}
                            slidesPerView={'auto'}
                            slidesOffsetBefore={20}
                            slidesOffsetAfter={20}
                            className="home-slide__famous">
                            {domesticTravel.map((item) => (
                                <SwiperSlide key={item.id}>
                                    <a href="" className="slide__link">
                                        <div className="slide__img">
                                            <img src={domestic_jeju_01} />
                                        </div>
                                        <p>{item.name}</p>
                                    </a>
                                </SwiperSlide>
                            ))}
                        </Swiper>
                    </div>
                </section>
                <section
                    aria-label="해외 인기 여행지"
                    className="home-slide-section contents flex-column">
                    <div className="home-slide__title">
                        <div className="home-slide__flex">
                            <h2 className="slide__title">해외 인기 여행지</h2>
                        </div>
                    </div>
                    <div className="home-slide__cont">
                        {/* 추후 컴포넌트로 빼야함 */}
                        <Swiper
                            spaceBetween={12}
                            slidesPerView={'auto'}
                            slidesOffsetBefore={20}
                            slidesOffsetAfter={20}
                            className="home-slide__famous">
                            {overseasTravel.map((item) => (
                                <SwiperSlide key={item.id}>
                                    <a href="" className="slide__link">
                                        <div className="slide__img">
                                            <img src={domestic_jeju_01} />
                                        </div>
                                        <p>{item.name}</p>
                                    </a>
                                </SwiperSlide>
                            ))}
                        </Swiper>
                    </div>
                </section>
>>>>>>> master
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
                    <select value={searchType} onChange={(e) => setSearchType(e.target.value)}>
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
