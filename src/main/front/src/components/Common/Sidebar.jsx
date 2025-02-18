import 'react';
import MenuItems from './MenuItems';

// sihong 20250210 - 사이드바 메뉴 컴포넌트 추가
const Sidebar = ({ onClose }) => {

    const travelMenus = [
        {id:"1", name:"국내숙소"},
        {id:"2", name:"해외숙소"},
        {id:"3", name:"항공"},
        {id:"4", name:"항공+숙소"},
        {id:"5", name:"레저·티켓"},
        {id:"6", name:"렌터카"},
        {id:"7", name:"공간대여"},
    ];

    const otherMenus = [
        {id:"1", name:"비회원 예약조회"},
        {id:"2", name:"이벤트"},
        {id:"3", name:"고객센터"},
    ]

    return (
        <aside className="side-bar-wrapper">
            <div className="side-bar">
                <section className="side-bar__action-bar">
                    <button onClick={onClose}>X 닫기</button>
                </section>
                <section className="side-bar__contents">
                    {/* 이미지 영역 */}
                    <div></div>
                    {/* 회원가입 / 로그인 버튼 */}
                    <div></div>
                    {/* 메뉴 리스트 */}
                    <div className="menu-group-wrapper">
                        <span>모든 여행</span>
                        <ul className="menu-items-wrapper">
                            {travelMenus.map((item) => (
                                <MenuItems key={item.id} menuName={item.name} />
                            ))}
                        </ul>
                    </div>
                    <div className="menu-group-wrapper">
                        <ul>
                            {otherMenus.map((item) => (
                                <MenuItems key={item.id} menuName={item.name} />
                            ))}
                        </ul>
                    </div>
                </section>
            </div>
        </aside>
    );
}

export default Sidebar;