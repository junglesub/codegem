import "./NewUI.css";
import FeedItemNew from "../components/FeedItemNew/FeedItemNew";
import WeatherData from "../components/FeedItemNew/WeatherData";
import { useEffect, useState } from "react";

// const allFeeds = [
//   {
//     author: "실명카톡방2",
//     createdAt: "2024-07-05",
//     id: 1,
//     content: `
// 친구 대신 올립니다!

// 🏡원룸 양도합니다🏡

// 📌위치: 유야 근처(카풀 잡기 좋은 위치)
// 📌크기: 주방 분리형 원룸
// 📌가격: 월 38(관리비 포함)/ 전기세, 가스비 별도
// 📌옵션: 책상, 의자, 에어컨, 냉장고, 전자레인지, 세탁기, 전신거울, 침대
// 📌양도기간: 8월 1일~8월 31일
// 📌여자분들만 연락부탁드려요 !(담배X)

// 문의: 010-1234-5678

// https://open.kakao.com/o/
//     `,
//   },
//   {
//     author: "실명카톡방2",
//     createdAt: "2024-07-05",
//     id: 2,
//     img: "pard.jpg",
//     content: `
// ✨PARD 3기 데모데이 D-7✨

// 안녕하세요!
// 포항시 IT 협업 동아리 PARD 입니다 🌊

// 3기 데모데이에 오셔서 파드 사람들이 직접 기획/디자인/개발 한 서비스를 만나보세요!

// 🎈데모데이 참여 혜택🎈
// 1️⃣  PARD 3기의 5개의 서비스 제일 먼저 만나보기
// 2️⃣  에어팟, 스탠리, 굿즈 등 풍성한 경품 받기 (음악감상)
// 3️⃣  맛있는 케이터링과 함께 즐기기!

//     `,
//   },
// ];

function NewUI() {
  const [allFeeds, setAllFeed] = useState([]);
  useEffect(() => {
    (async () => {
      const data = await (await fetch("/api/kafeed/scrolllist")).json();
      setAllFeed(
        data.map((dd) => ({
          author: "실명카톡방2",
          createdAt: new Date(dd.sentAt * 1000),
          id: dd.id,
          content: dd.message,
          files: dd.files,
          img: dd.files[0], // Temp just for testing
        }))
      );
    })();
  }, []);
  return (
    <div className="NewUI">
      <div className="header">
        <h1>Handong Feed</h1>
      </div>

      <div className="list">
        <WeatherData />
        {allFeeds.length === 0 && <div>No Entry..</div>}
        {allFeeds
          .sort((a, b) => (a.createdAt < b.createdAt ? 1 : -1))
          .map((item) => (
            <FeedItemNew item={item} />
          ))}
      </div>
    </div>
  );
}

export default NewUI;
