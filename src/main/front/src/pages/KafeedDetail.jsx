import { authJwtAtom } from "../recoil/authAtom";
import { useRecoilValue } from "recoil";
import LoginPage from "../components/LoginPage";
import MainDisplay from "../components/MainDisplay";
import { Card, CardContent, Typography } from "@mui/material";
import { useFetchBe } from "../tools/api";

function KafeedDetail() {
  const jwtValue = useRecoilValue(authJwtAtom);
  const fetchBe = useFetchBe();

  if (!jwtValue) return <LoginPage />;
  return (
    <MainDisplay>
      <Card sx={{ my: 2 }}>
        <CardContent>
          <Typography variant="h5" component="div" align="center">
            aa
          </Typography>
        </CardContent>
      </Card>
    </MainDisplay>
  );
}

export default KafeedDetail;
