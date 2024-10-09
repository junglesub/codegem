import { useRecoilState } from "recoil";
import { feedCountAtom } from "../recoil/feedAtom";
import { useFetchBe } from "../tools/api";

export const useFeedCount = () => {
  const [count, setCount] = useRecoilState(feedCountAtom);
  const fetchBe = useFetchBe();

  const getCount = async () => {
    const data = await fetchBe("/kafeed/count");
    setCount(data.count);
  };

  if (count === -1) {
    getCount();
  }

  return [Math.max(count, 0), getCount];
};
