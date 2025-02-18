import "react";
import Header from "../components/Common/Header";
import InfoItem from "../components/profile/InfoItem";

const ProfilePage = () => {

    const profileInfo = [
        {id:"1", title:"닉네임", contents:"썽인"},
        {id:"2", title:"예약자 이름", contents:"미입력 (앱에서 입력해 주세요.)"},
        {id:"3", title:"휴대폰 번호", contents:"0104674****"},
        {id:"4", title:"생년월일", contents:"19**년 **월 **일"},
        {id:"5", title:"성별", contents:"**"},
    ]
    
    return (
        <div className="App">
            <Header />
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