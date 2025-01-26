import * as React from "react";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import Divider from "@mui/material/Divider";
import ListItemText from "@mui/material/ListItemText";
import ListItemAvatar from "@mui/material/ListItemAvatar";
import Avatar from "@mui/material/Avatar";
import Typography from "@mui/material/Typography";
import { Box, Chip, Link } from "@mui/material";
import { Link as RouterLink } from "react-router-dom";

export default function SolvedList({ item }) {
  return (
    <Box sx={{ bgcolor: "background.paper", my: 1 }}>
      <Box display="flex" gap={1} sx={{ p: 2 }}>
        <Chip label={item.labels[0]?.name} size="small" />
        <Typography>
          <Link
            component={RouterLink}
            to={`/review/${item.userId}/${item.number}`}
          >
            {item.title}
          </Link>
        </Typography>
      </Box>
      {/* <Box display="flex" gap={1} sx={{ p: 1 }}>
        <Typography>
          {item.lastCreatedDate.split("T")[0]} 푼 3개의 문제 더보기
        </Typography>
      </Box> */}
      {/* <Box display="flex" gap={1} sx={{ p: 1 }}>
        <Chip label="백준" size="small" />
        <Typography>등장하지 않는 문자의 합</Typography>
      </Box> */}
    </Box>
  );
  // return (
  //   <List
  //     sx={{
  //       width: "100%",
  //       // maxWidth: 360,
  //       bgcolor: "background.paper",
  //     }}
  //   >
  //     <ListItem alignItems="flex-start">
  //       {/* <ListItemAvatar>
  //         <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
  //       </ListItemAvatar> */}
  //       <ListItemText
  //         primary="Brunch this weekend?"
  //         secondary={
  //           <React.Fragment>
  //             <Typography
  //               component="span"
  //               variant="body2"
  //               sx={{ color: "text.primary", display: "inline" }}
  //             >
  //               Ali Connors
  //             </Typography>
  //             {" — I'll be in your neighborhood doing errands this…"}
  //           </React.Fragment>
  //         }
  //       />
  //     </ListItem>
  //     {/* <Divider variant="inset" component="li" /> */}
  //   </List>
  // );
}
