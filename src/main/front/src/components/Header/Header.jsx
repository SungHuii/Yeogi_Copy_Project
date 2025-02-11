import 'react';
import Sidebar from "./Sidebar";

// sihong 20250210 - 헤더 컴포넌트 추가
const Header = () => {
    return (
        <header>
            <h1>Header</h1>
            <Sidebar />
        </header>
    );
}

export default Header;