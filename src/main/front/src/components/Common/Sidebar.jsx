import 'react';
import MenuItems from './MenuItems';
import { Link, useNavigate } from 'react-router-dom';
import BtnLogin from './BtnLogin';
import BtnProfile from './BtnProfile';

// sihong 20250210 - 사이드바 메뉴 컴포넌트 추가
const Sidebar = ({ onClose }) => {
    const travelMenus = [
        { id: '1', name: '국내숙소' },
        { id: '2', name: '해외숙소' },
        { id: '3', name: '항공' },
        { id: '4', name: '항공+숙소' },
        { id: '5', name: '레저·티켓' },
        { id: '6', name: '렌터카' },
        { id: '7', name: '공간대여' },
    ];

    const otherMenus = [
        { id: '1', name: '비회원 예약조회' },
        { id: '2', name: '이벤트' },
        { id: '3', name: '고객센터' },
    ];

    //페이지 라우팅을 위해 useNavigate 선언
    const navigate = useNavigate();

    //로그인 페이지 라우팅
    const goLogin = () => {
        navigate('/login');
    };

    //프로필 페이지 라우팅
    const goProfile = () => {
        navigate('/profile');
    };

    return (
        <aside className="side-bar-wrapper">
            <div className="side-bar">
                <section className="side-bar__action-bar">
                    <button onClick={onClose}>
                        <svg
                            width="20"
                            height="20"
                            viewBox="0 0 20 20"
                            fill="none"
                            xmlns="http://www.w3.org/2000/svg"
                            className="icn-close">
                            <path
                                d="M10 8.586l5.657-5.657 1.414 1.414L11.414 10l5.657 5.657-1.414 1.414L10 11.414l-5.657 5.657-1.414-1.414L8.586 10 2.929 4.343 4.343 2.93 10 8.586z"
                                fill="current"></path>
                        </svg>
                    </button>
                </section>
                <section className="side-bar__contents">
                    <div className="menu-group-wrapper">
                        {/* 이미지 영역 */}
                        <Link href="/benefit/elite"></Link>
                        {/* 회원가입 / 로그인 버튼 */}
                        <BtnLogin onClick={goLogin} />
                        {/* 내 정보 관리 */}
                        <BtnProfile onClick={goProfile} />
                    </div>
                    {/* 메뉴 리스트 */}
                    <div className="menu-group-wrapper">
                        <span className="menu-group__title">모든 여행</span>
                        <ul className="menu-items-wrapper">
                            {travelMenus.map((item) => (
                                <MenuItems key={item.id} menuName={item.name} />
                            ))}
                        </ul>
                    </div>
                    <div className="menu-group-wrapper">
                        <ul className="menu-items-wrapper">
                            {otherMenus.map((item) => (
                                <MenuItems key={item.id} menuName={item.name} />
                            ))}
                        </ul>
                    </div>
                </section>
            </div>
        </aside>
    );
};

export default Sidebar;
