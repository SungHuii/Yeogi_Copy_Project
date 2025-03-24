import "react";
import { Routes, Route } from 'react-router-dom';
import Header from "../components/Common/Header";
import InfoItem from "../components/profile/InfoItem";
import ProfileNavigationList from "../components/profile/ProfileNavigationList";
import ProfileMyInfo from "../components/profile/ProfileMyInfo";
import ProfileSettings from "../components/profile/ProfileSettings";
import ProfileCoupons from "../components/profile/ProfileCoupons";
import ProfileFavorites from "../components/profile/ProfileFavorites";
import ProfilePoints from "../components/profile/ProfilePoints";
import ProfileReservations from "../components/profile/ProfileReservations";

const ProfilePage = () => {

    const profileInfo = [
        {id:"1", title:"닉네임", contents:"썽인"},
        {id:"2", title:"예약자 이름", contents:"미입력 (앱에서 입력해 주세요.)"},
        {id:"3", title:"휴대폰 번호", contents:"0104674****"},
        {id:"4", title:"생년월일", contents:"19**년 **월 **일"},
        {id:"5", title:"성별", contents:"**"},
    ]

    const navigationList = [
        { title:"예약 내역", link: "/profile/reservations"},
        { title:"찜 목록", link: "/profile/favorites"},
        { title:"포인트", link: "/profile/points"},
        { title:"쿠폰", link: "/profile/coupons"},
        { title:"내 정보 관리", link: "/profile/info"},
        { title:"설정", link: "/profile/settings"},
    ]
    
    return (
        <div className="App">
            <Header />
            <div className="profile-layout">
                <nav className="profile-layout__navigation">
                    <ul>
                        {navigationList.map((item) => (
                            <ProfileNavigationList
                                key={item.title}
                                title={item.title}
                                link={item.link}
                            />
                        ))}
                    </ul>
                </nav>
                <section className="profile-layout__content">
                    <Routes>
                        <Route path="reservations" element={<ProfileReservations />} />
                        <Route path="favorites" element={<ProfileFavorites />} />
                        <Route path="points" element={<ProfilePoints />} />
                        <Route path="coupons" element={<ProfileCoupons />} />
                        <Route path="info" element={<ProfileMyInfo />} />
                        <Route path="settings" element={<ProfileSettings />} />
                    </Routes>
                </section>
            </div>
            <span>프로필 페이지입니다.</span>
            <h2>내 정보 관리</h2>
            <h3>회원 정보</h3>
            <p>현재 정보 수정은 여기어때 앱에서 가능해요.</p>

            {profileInfo.map((item) => (
                <InfoItem key={item.id} infoTitle={item.title} infoContents={item.contents} />
            ))}
        </div>
    );
}

export default ProfilePage;