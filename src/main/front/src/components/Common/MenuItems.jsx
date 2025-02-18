import "react";

// sihong 20250210 - 메뉴 컴포넌트 추가
const MenuItems = ({ menuName }) => {
    return (
        <li><a>{menuName}</a></li>
    )
}

export default MenuItems;