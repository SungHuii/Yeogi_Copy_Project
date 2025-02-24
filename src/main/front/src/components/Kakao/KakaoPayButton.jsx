import { useEffect } from "react";
import axios from "axios";

const KakaoPayButton = () => {

    useEffect(() => {
        if (window.Kakao) {
            window.Kakao.init('370A1EB211EEEEC89782');
        }
    }, []);

    const handlePayment = () => {
        axios.post('http://localhost:8080/kakao-pay/pay/ready/1')
            .then((res) => {
                const { next_redirect_pc_url } = res.data;
                window.location.href = next_redirect_pc_url;
            })
            .catch((err) => {
                console.error("결제 요청 실패:", err);
            });
    };

    return (
        <button onClick={handlePayment}>카카오페이로 결제하기</button>
    );
};

export default KakaoPayButton;
