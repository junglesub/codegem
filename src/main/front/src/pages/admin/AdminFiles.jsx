import InfiniteScroll from "react-infinite-scroller";
import AdminPage from "./AdminPage";
import FeedCard from "../../components/FeedCard";
import useLoadData from "../../hooks/useLoadData";
import { Box, Paper } from "@mui/material";
import AdminFilesComp from "../../admin/Files";

function AdminFiles() {
  return (
    <AdminPage>
      <AdminFilesComp />
    </AdminPage>
  );
}

export default AdminFiles;
