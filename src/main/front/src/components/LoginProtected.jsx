import { useRecoilState, useRecoilValue } from "recoil";
import { authJwtAtom } from "../recoil/authAtom";
import { userDetailAtom } from "../recoil/userAtom";
import { Navigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { fetchBe } from "../tools/api";

function LoginProtected({ comp: Comp }) {
  const jwtValue = useRecoilValue(authJwtAtom);
  const [userData, setUserData] = useRecoilState(userDetailAtom);
  const [userDataLoading, setUserDataLoading] = useState(true);
  const [userDataError, setUserDataError] = useState();

  useEffect(() => {
    fetchBe(jwtValue, "/tbuser/me")
      .then((json) => {
        console.log("Got User Data", json);
        if (json.db) setUserData(json);
        else setUserData(null);
        setUserDataLoading(false);
      })
      .catch((e) => setUserDataError(e.message));
  }, [jwtValue]);

  if (jwtValue) {
    if (typeof Comp === "object") return <>{Comp}</>;
    // if (userDataError)
    //   return <div style={{ color: "pink" }}>Error: {userDataError}</div>;
    if (userDataLoading)
      return <div style={{ color: "white" }}>Loading User Data...</div>;

    // if (!!userData) {
    return <Comp />;
    // } else {
    //   return <Navigate to="/" />;
    // }
  }

  return <Navigate to="/land" />;
}

export default LoginProtected;
